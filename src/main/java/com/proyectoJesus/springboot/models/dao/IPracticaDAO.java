package com.proyectoJesus.springboot.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proyectoJesus.springboot.models.entity.Practica;
import com.proyectoJesus.springboot.models.entity.Usuario;

public interface IPracticaDAO extends CrudRepository<Practica, Long>{

	@Query(value="select p.id_practica, p.titulo, p.comentario, p.asignatura_id_asignatura from usuarios u, practicas p, alumnos_practicas ap "
			+ "where u.id_usuario = :id and u.id_usuario = ap.id_usuario and p.id_practica = ap.id_practica", nativeQuery=true)
	public List<Practica> findPracticasByAlumno(Long id);
	

}
