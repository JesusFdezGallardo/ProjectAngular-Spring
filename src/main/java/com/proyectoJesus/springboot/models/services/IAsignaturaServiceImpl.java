package com.proyectoJesus.springboot.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoJesus.springboot.models.dao.IAsignaturaDAO;
import com.proyectoJesus.springboot.models.entity.Asignatura;
import com.proyectoJesus.springboot.models.entity.Usuario;

@Service
public class IAsignaturaServiceImpl implements IAsignaturaService {

	@Autowired
	private IAsignaturaDAO asignaturaDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Asignatura> findAll() {
		return (List<Asignatura>) asignaturaDAO.findAll();
	}

	@Override
	@Transactional
	public Asignatura save(Asignatura asignatura) {
		// TODO Auto-generated method stub
		return asignaturaDAO.save(asignatura);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		asignaturaDAO.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Asignatura findById(Long id) {
		//orElse para que devuelva null si no la encuentra y evitar errores
		return asignaturaDAO.findById(id).orElse(null);
	}

}
