package com.proyectoJesus.springboot.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.proyectoJesus.springboot.models.dao.IUsuarioDao;
import com.proyectoJesus.springboot.models.entity.Rol;
import com.proyectoJesus.springboot.models.entity.Usuario;
import com.proyectoJesus.springboot.models.services.IAsignaturaService;
import com.proyectoJesus.springboot.models.services.IUsuarioService;

@CrossOrigin(origins = { "http://localhost:4200" }) // Comunica cliente servidor--> Angular - Spring
@RestController // Indica que es un Rest Controller
@RequestMapping("/api") // Indica la URL
public class UsuarioRestController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/usuarios") // Mapeamos la URL
	// Crea método index para listar usuarios
	public List<Usuario> index() {
		return usuarioService.findAll();
	}

//	//Buscar por ID
//	@GetMapping("/usuarios/{id}")
//	@ResponseStatus(HttpStatus.OK) //Mensaje que muestra. 200 = búsqueda correcta
//	public Usuario show(@PathVariable Long id) {
//		return usuarioService.finById(id);
//	}

	// Buscar por ID con la clase ResponseEntity de Spring para manejar mensajes de
	// error
	// Se usa ? para decir que es un tipo de dato genérico, no tiene porque ser
	// Usuario
	@GetMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.OK) // Mensaje que muestra. 200 = búsqueda correcta
	public ResponseEntity<?> show(@PathVariable Long id) {
		Usuario usuario = null;
		// Tipo da dato MAP que almacene datos asociados a un nombre
		Map<String, Object> response = new HashMap<>();
		try {
			usuario = usuarioService.finById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error en la consulta en la BBDD!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Error por id
		if (usuario == null) {
			response.put("mensaje", "El usuario con el id: " + id + " no existe en la BD!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		// Devuelve con argumento tipo de dato y la respuesta Http Status
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED) // Mensaje que muestra. 201 = creado contenido. Anotacion VALID para comprobar
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result) {

		Usuario usuarioNuevo = null;
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
			usuarioNuevo = usuarioService.save(usuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar en la BBDD!");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario se ha creado con éxito");
		response.put("usuario", usuarioNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {

		Usuario usuarioActual = usuarioService.finById(id); // Modifico usuario con el usuario registrado en la bbdd
		Usuario usuarioUpdate = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
		}

		// Error por id
		if (usuarioActual == null) {
			response.put("error",
					"El usuario no se puede editar: ".concat(id.toString().concat(" no existe el ID en la BD!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			usuarioActual.setNombre(usuario.getNombre());
			usuarioActual.setPass(usuario.getPass());
			usuarioActual.setApellido(usuario.getApellido());
			usuarioActual.setCorreoElectronico(usuario.getCorreoElectronico());
			usuarioActual.setRol(usuario.getRol());
			usuarioUpdate = usuarioService.save(usuarioActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el usuario en la BBDD!");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido actualizado con éxito");
		response.put("usuario", usuarioUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el usuario en la BBDD!");
			response.put("error", e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/usuarios/roles")
	public List<Rol> listaRoles(){
		return usuarioService.findAllRoles();
	}
}
