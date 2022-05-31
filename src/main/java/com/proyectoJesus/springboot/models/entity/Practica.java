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
		
//	private Long nota;
	
//	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
//	@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler" }, allowSetters = true) 
//	private Asignatura asignaturaPractica; 
	
////	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
////	@JsonIgnoreProperties(value= {"hibernateLazyInitializer", "handler" }, allowSetters = true) 
////	@JoinTable(name = "alumnos_practicas")
////	private List<Usuario> alumnosPractica;
//	
////	public Practica() {
////		this.alumnosPractica = new ArrayList<>(); 
////	}
	
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

//	public Long getNota() {
//		return nota;
//	}
//
//	public void setNota(Long nota) {
//		this.nota = nota;
//	}

	public Long getIdPractica() {
		return idPractica;
	}

	public void setIdPractica(Long idPractica) {
		this.idPractica = idPractica;
	}

//	public Asignatura getAsignaturaPractica() {
//		return asignaturaPractica;
//	}
//
//	public void setAsignaturaPractica(Asignatura asignaturaPractica) {
//		this.asignaturaPractica = asignaturaPractica;
//	}

//	public List<Usuario> getAlumnosPractica() {
//		return alumnosPractica;
//	}
//
//	public void setAlumnosPractica(List<Usuario> alumnosPractica) {
//		this.alumnosPractica = alumnosPractica;
//	}
	
	
}
