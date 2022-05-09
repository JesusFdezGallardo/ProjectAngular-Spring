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
}
