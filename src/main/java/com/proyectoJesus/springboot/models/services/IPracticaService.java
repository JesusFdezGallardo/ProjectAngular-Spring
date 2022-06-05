package com.proyectoJesus.springboot.models.services;

import java.util.List;

import com.proyectoJesus.springboot.models.entity.Practica;

public interface IPracticaService {

	
	//Mostrar todas las practicas
	public List<Practica> findAll();

	//Buscar usuario por Id
	public Practica findById(Long id);
	
	//Metodos CRUD
	public Practica save(Practica practica);
	public void delete (Long id);
	
	//Buscar practicas por alumno
	public List<Practica> findPracticasByAlumno(Long id);
}
