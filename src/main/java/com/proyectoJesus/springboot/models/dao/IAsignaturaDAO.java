package com.proyectoJesus.springboot.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proyectoJesus.springboot.models.entity.Asignatura;

public interface IAsignaturaDAO extends CrudRepository<Asignatura, Long>{
	
}
