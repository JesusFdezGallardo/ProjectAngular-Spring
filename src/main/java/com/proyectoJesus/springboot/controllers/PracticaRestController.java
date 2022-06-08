package com.proyectoJesus.springboot.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoJesus.springboot.models.entity.Asignatura;
import com.proyectoJesus.springboot.models.entity.Practica;
import com.proyectoJesus.springboot.models.entity.Rol;
import com.proyectoJesus.springboot.models.entity.Usuario;
import com.proyectoJesus.springboot.models.services.IAsignaturaService;
import com.proyectoJesus.springboot.models.services.IPracticaService;
import com.proyectoJesus.springboot.models.services.IUsuarioService;

@CrossOrigin(origins = { "http://localhost:4200" , "https://proyectojesus-springboot.herokuapp.com"}) // Comunica cliente servidor--> Angular - Spring
@RestController // Indica que es un Rest Controller
@RequestMapping("/api") // Indica la URL
public class PracticaRestController {

	@Autowired
	private IPracticaService iPracticaService;
	
	@Autowired
	private IAsignaturaService iAsignaturaService;
	
	@Autowired
	private IUsuarioService iUsuarioService;
	
	@GetMapping("/practicas") // Mapeamos la URL
	// Crea método index para listar usuarios
	public List<Practica> index() {
		return iPracticaService.findAll();
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_PROFESOR", "ROLE_ALUMNO"})
	@GetMapping("/practicas/{id}")
	@ResponseStatus(HttpStatus.OK) // Mensaje que muestra. 200 = búsqueda correcta
	public ResponseEntity<?> show(@PathVariable Long id) {
		Practica practicaNueva = null;
		// Tipo da dato MAP que almacene datos asociados a un nombre
		Map<String, Object> response = new HashMap<>();
		try {
			practicaNueva = iPracticaService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error en la consulta en la BBDD!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Error por id
		if (practicaNueva == null) {
			response.put("mensaje", "La practica con el id: " + id + " no existe en la BD!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		// Devuelve con argumento tipo de dato y la respuesta Http Status
		return new ResponseEntity<Practica>(practicaNueva, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_PROFESOR"})	
	@PostMapping("/practicas/{id}")
	@ResponseStatus(HttpStatus.CREATED) // Mensaje que muestra. 201 = creado contenido. Anotacion VALID para comprobar
	public ResponseEntity<?> create(@Valid @RequestBody Practica practica, BindingResult result, @PathVariable Long id) {

		Practica practicaNueva = null;
		Asignatura asignatura = iAsignaturaService.findById(id);
		System.out.println(asignatura.getId());
		Map<String, Object> response = new HashMap<>();

		// Comprobamos si hay errores antes de guardar usuario
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {	
			List<Usuario> listaAlumnos = new ArrayList<>() ;
			
			listaAlumnos.addAll(asignatura.getAlumnos());
			practica.setPracticas(listaAlumnos);
			practica.setAsignatura(asignatura);
						
			practicaNueva = iPracticaService.save(practica);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar en la BBDD!");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario se ha creado con éxito");
		response.put("usuario", practicaNueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/practicas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			iPracticaService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la práctica en la BBDD!");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La practica ha sido eliminada con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_PROFESOR", "ROLE_ALUMNO"})
	@GetMapping("/usuarios/practicas/{id}")
	@ResponseStatus(HttpStatus.OK) // Mensaje que muestra. 200 = búsqueda correcta
	public List<Practica> mostrarPracticasAlumno(@PathVariable Long id) {
		return iPracticaService.findPracticasByAlumno(id);
	}
}
