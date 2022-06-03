package com.proyectoJesus.springboot.models.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoJesus.springboot.models.dao.IUsuarioDao;
import com.proyectoJesus.springboot.models.entity.Rol;
import com.proyectoJesus.springboot.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {

	// Inyeccion del DAO en cualquier otro componente o clase de la aplicacion
	//Con autowired
	@Autowired
	private IUsuarioDao usuariodao; //Interfaz IUsuarioDAO
	//Clase Logger para realizar el login a la aplicacion y almacenar los datos
	private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Override
	// Transaccion sólo de lectura. No es necesario porque lo da la clase
	// CrudRepository. SOLO USAMOS METODOS PROPIOS
	@Transactional(readOnly = true)
	public List<Usuario> findAll() { //findALL método propio CRUDRepository
		return (List<Usuario>) usuariodao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario finById(Long id) {
		return usuariodao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuariodao.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuariodao.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Rol> findAllRoles() {
		return usuariodao.findAllRoles();
	}

	@Override
	@Transactional(readOnly = true)
	//Metodo de la clase UserDetailsService para realizar el login
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuariodao.findByUsuario(username);

		if (usuario == null) {
			logger.error("Error: no existe el usuario '" + username + "' en el sistema!");
			throw new UsernameNotFoundException(
					"Error en el login: no existe el usuario '" + username + "' en el sistema!");
		}

		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority())).collect(Collectors.toList());

		return new User(usuario.getUsuario(), usuario.getPass(), true, true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsuario(String username) {
		return usuariodao.findByUsuario(username);
	}

	@Override
	public List<Usuario> findByNombre(String nombre) {
		return usuariodao.findByNombre(nombre);
	}

	@Override
	public List<Usuario> findByRolProfesor() {
		return usuariodao.findByRolProfesor();
	}

	@Override
	public List<Usuario> findByRolAlumno() {
		return usuariodao.findByRolAlumno();
	}

	@Override
	public List<Usuario> findAlumnos(String consulta) {
		return usuariodao.findAlumnos(consulta);
	}

}
