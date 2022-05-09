package com.proyectoJesus.springboot.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoJesus.springboot.models.entity.Asignatura;
import com.proyectoJesus.springboot.models.entity.Usuario;
import com.proyectoJesus.springboot.models.services.IAsignaturaService;

@CrossOrigin(origins = { "http://localhost:4200" }) // Comunica cliente servidor--> Angular - Spring
@RestController // Indica que es un Rest Controller
@RequestMapping("/api") // Indica la URL
public class AsignaturaRestController {
	
	@Autowired
	private IAsignaturaService asignaturaService;
	
	@GetMapping("/asignaturas") // Mapeamos la URL
	// Crea método index para listar usuarios
	public List<Asignatura> index() {
		return asignaturaService.findAll();
	}
	
	@GetMapping("/asignaturas/{id}")
	@ResponseStatus(HttpStatus.OK) // Mensaje que muestra. 200 = búsqueda correcta
	public ResponseEntity<?> show(@PathVariable Long id) {
		Asignatura asignaturaNueva = null;
		// Tipo da dato MAP que almacene datos asociados a un nombre
		Map<String, Object> response = new HashMap<>();
		try {
			asignaturaNueva = asignaturaService.finById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error en la consulta en la BBDD!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Error por id
		if (asignaturaNueva == null) {
			response.put("mensaje", "La asignatura con el id: " + id + " no existe en la BD!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		// Devuelve con argumento tipo de dato y la respuesta Http Status
		return new ResponseEntity<Asignatura>(asignaturaNueva, HttpStatus.OK);
	}
	
	@PostMapping("/asignaturas")
	@ResponseStatus(HttpStatus.CREATED) // Mensaje que muestra. 201 = creado contenido. Anotacion VALID para comprobar
	public ResponseEntity<?> create(@Valid @RequestBody Asignatura asignatura, BindingResult result) {

		Asignatura asignaturaNueva = null;
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
			asignaturaNueva = asignaturaService.save(asignatura);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar en la BBDD!");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La asignatura se ha creado con éxito");
		response.put("usuario", asignaturaNueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/asignaturas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Asignatura asignatura, BindingResult result, @PathVariable Long id) {

		Asignatura asignaturaActual = asignaturaService.finById(id); // Modifico usuario con el usuario registrado en la bbdd
		Asignatura asignaturaActualizada = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
		}
		// Error por id
		if (asignaturaActual == null) {
			response.put("error",
					"La asignatura no se puede editar: ".concat(id.toString().concat(" no existe el ID en la BD!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			asignaturaActual.setNombre(asignatura.getNombre());
			asignaturaActualizada = asignaturaService.save(asignaturaActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la asignatura en la BBDD!");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La asignatura ha sido actualizado con éxito");
		response.put("usuario", asignaturaActualizada);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/asignaturas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			asignaturaService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la asignatura en la BBDD!");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La asignatura ha sido eliminada con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
