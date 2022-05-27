package com.proyectoJesus.springboot.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.proyectoJesus.springboot.models.entity.Rol;
import com.proyectoJesus.springboot.models.entity.Usuario;

/*
 * Interfaz para operaciones CRUD básicas. Incluimos clase y dato id
 */
public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	@Query("from Rol")
	public List<Rol> findAllRoles();
		
	@Query(value="select u.id_usuario, u.apellido, u.correo, u.nombre, u.pass, u.usuario from usuarios u, roles r, usuarios_roles ur where r.nombre = 'ROLE_ALUMNO' and ur.role_id = r.id and ur.usuario_id = u.id_usuario", nativeQuery=true)
	public List<Usuario> findByRolAlumno();
	
	@Query(value="select u.id_usuario, u.apellido, u.correo, u.nombre, u.pass, u.usuario from usuarios u, roles r, usuarios_roles ur where r.nombre = 'ROLE_PROFESOR' and ur.role_id = r.id and ur.usuario_id = u.id_usuario", nativeQuery=true)
	public List<Usuario> findByRolProfesor();
		
	@Query
	public Usuario findByUsuario(String usuario);
		
	@Query()
	public List<Usuario> findByNombre(String nombre);
}
