package com.proyectoJesus.springboot.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Especificamos que el nombre no puede ser null
	@Column(nullable = false, name = "id_usuario")
	private Long idUsuario;

	// Anotación para validar el nombre
	@NotEmpty(message = "no puede estar vacío") // Anotacion para cambiar el idioma y mensajes de error
	@Size(min = 4, max = 12)
	@Column(nullable = false, name = "nombre")
	private String nombre;
	
	@NotEmpty(message = "no puede estar vacío") // Anotacion para cambiar el idioma y mensajes de error
	@Column(nullable = false, name = "usuario", unique = true)
	private String usuario;

	@NotEmpty(message = "no puede estar vacío")
	@Column(name = "pass")
	private String pass;

	@NotEmpty(message = "no puede estar vacío")
	@Column(name = "apellido")
	private String apellido;

	// El email es único y no se puede repetir
	@NotEmpty(message = "no puede estar vacío")
	@Email(message = " la dirección de correo no está bien formada") // Validacion de formato de correo
	@Column(nullable = false, unique = true, name = "correo")
	private String correoElectronico;

	@NotNull(message= " el campo rol no puede estar vacío")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="usuarios_roles", joinColumns= @JoinColumn(name="usuario_id"),
	inverseJoinColumns=@JoinColumn(name="role_id"),
	uniqueConstraints= {@UniqueConstraint(columnNames= {"usuario_id", "role_id"})})
	private List<Rol> roles;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "alumnos", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"alumnos", "id", "hibernateLazyInitializer", "handler" }) 
	private List<Asignatura> asignaturas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profesor", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"alumnos", "asignaturas", "practicas", "hibernateLazyInitializer", "handler" }) 
	private List<Asignatura> asignaturasProfesor;
	
	//Constructor arraylist Asignaturas	
	public Usuario() {
		this.asignaturas = new ArrayList<>();
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", pass=" + pass + ", apellido=" + apellido
				+ ", correoElectronico=" + correoElectronico + "]";
	}

	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public List<Asignatura> getAsignaturasProfesor() {
		return asignaturasProfesor;
	}

	public void setAsignaturasProfesor(List<Asignatura> asignaturasProfesor) {
		this.asignaturasProfesor = asignaturasProfesor;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	
	
}
