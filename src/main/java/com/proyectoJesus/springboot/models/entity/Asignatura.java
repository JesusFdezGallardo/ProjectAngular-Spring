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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "asignaturas")
public class Asignatura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAsignatura;

	@NotEmpty(message = "no puede estar vacio")
	@Column(nullable = false)
	private String nombre;

	// Muchas asignaturas están asociadas a un profesor
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JsonIgnoreProperties({"asignaturas", "rol","asignaturasProfesor", "id", "hibernateLazyInitializer", "handler" }) 
	@JoinTable(name = "asignaturas_profesores", joinColumns = @JoinColumn(name = "id_asignatura"), inverseJoinColumns = @JoinColumn(name = "id_usuario"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "id_asignatura", "id_usuario" }) })
	private Usuario profesor;

	//cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JsonIgnoreProperties({"asignaturas", "id", "asignaturasProfesor","rol" ,"hibernateLazyInitializer", "handler" }) 
	@JoinTable(name = "asignaturas_alumnos", joinColumns = @JoinColumn(name = "id_asignatura"), inverseJoinColumns = @JoinColumn(name = "id_usuario"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "id_asignatura", "id_usuario" }) })
	private List<Usuario> alumnos;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JsonIgnoreProperties({"asignaturas", "id", "rol" ,"hibernateLazyInitializer", "handler" }) 
	@JoinTable(name = "asignaturas_practicas", joinColumns = @JoinColumn(name = "id_asignatura"), inverseJoinColumns = @JoinColumn(name = "id_practica"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "id_asignatura", "id_practica" }) })
	private List<Practica> practicas; 
	
	public Asignatura() {
		this.alumnos = new ArrayList<>();
		this.practicas = new ArrayList<>();
	}

	public List<Usuario> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Usuario> alumnos) {
		this.alumnos = alumnos;
	}

	public Long getId() {
		return idAsignatura;
	}

	public void setId(Long id) {
		this.idAsignatura = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(Long idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	public Usuario getProfesor() {
		return profesor;
	}

	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}

	public List<Practica> getPracticas() {
		return practicas;
	}

	public void setPracticas(List<Practica> practicas) {
		this.practicas = practicas;
	}

}
