package com.proyectoJesus.springboot.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.proyectoJesus.springboot.models.entity.Practica;

public interface IPracticaDAO extends CrudRepository<Practica, Long>{

	
}
