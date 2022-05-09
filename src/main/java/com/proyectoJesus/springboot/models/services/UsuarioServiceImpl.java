package com.proyectoJesus.springboot.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoJesus.springboot.models.dao.IUsuarioDao;
import com.proyectoJesus.springboot.models.entity.Rol;
import com.proyectoJesus.springboot.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	//Inyeccion del DAO en cualquier otro componente o clase de la aplicacion
	@Autowired
	private IUsuarioDao usuariodao;

	@Override
	//Transaccion sólo de lectura. No es necesario porque lo da la clase CrudRepository. SOLO USAMOS METODOS PROPIOS
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuariodao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario finById(Long id) {
		// TODO Auto-generated method stub
		return usuariodao.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuariodao.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuariodao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Rol> findAllRoles() {
		return usuariodao.findAllRoles();
	}

}
