package com.proyectoJesus.springboot.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoJesus.springboot.models.dao.IPracticaDAO;
import com.proyectoJesus.springboot.models.entity.Practica;

@Service
public class PracticaServiceImpl  implements IPracticaService{

	@Autowired
	private IPracticaDAO iPracticaDAO;
	
	@Override
	public List<Practica> findAll() {
		// TODO Auto-generated method stub
		return (List<Practica>) iPracticaDAO.findAll();
	}

	@Override
	public Practica findById(Long id) {
		// TODO Auto-generated method stub
		return iPracticaDAO.findById(id).orElse(null);
	}

	@Override
	public Practica save(Practica practica) {
		// TODO Auto-generated method stub
		return iPracticaDAO.save(practica);
	}

	@Override
	public void delete(Long id) {
		iPracticaDAO.deleteById(id);
		
	}

}
