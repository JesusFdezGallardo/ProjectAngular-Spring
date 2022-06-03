package com.proyectoJesus.springboot.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name= "practicas")
public class Practica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPractica; 
	
	private String titulo; 
	
	private String comentario; 
		
//	@ManyToOne(fetch = FetchType.LAZY, ma)
//	private Asignatura asignatura; 
	
	public Long getId() {
		return idPractica;
	}

	public void setId(Long id) {
		this.idPractica = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Long getIdPractica() {
		return idPractica;
	}

	public void setIdPractica(Long idPractica) {
		this.idPractica = idPractica;
	}
	
}
