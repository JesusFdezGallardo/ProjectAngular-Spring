package com.proyectoJesus.springboot.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proyectoJesus.springboot.models.entity.Practica;
import com.proyectoJesus.springboot.models.entity.Usuario;

public interface IPracticaDAO extends CrudRepository<Practica, Long>{

//	@Query(value="select u.id_usuario, u.apellido, u.correo, u.nombre, u.pass, u.usuario from usuarios u, roles r, usuarios_roles ur where r.nombre = 'ROLE_ALUMNO' and ur.role_id = r.id and ur.usuario_id = u.id_usuario", nativeQuery=true)
//	public List<Practica> findByRolAlumno();
}
