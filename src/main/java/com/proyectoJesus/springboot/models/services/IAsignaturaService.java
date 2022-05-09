package com.proyectoJesus.springboot.models.services;

import java.util.List;

import com.proyectoJesus.springboot.models.entity.Asignatura;
import com.proyectoJesus.springboot.models.entity.Usuario;

public interface IAsignaturaService {

	//Mostrar todas las asignaturas
	public List<Asignatura> findAll();

	//Buscar usuario por Id
	public Asignatura finById(Long id);
	
	//Metodos CRUD
	public Asignatura save(Asignatura asignatura);
	public void delete (Long id);
	
}
