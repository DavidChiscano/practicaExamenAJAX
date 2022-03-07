package com.example.demo.entity;

import java.io.Serializable;
import java.util.HashSet;



import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long idUsuario;
	
	@Column(name = "nombre_usuario")
	private String nombreUsuario;
	
	@Column(name = "contrasena")
	private String passwdUsuario;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE },mappedBy="usuarios")
	private Set<Rol> roles = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE },mappedBy="usuarios_comidas")
	private Set<Comida> comidas = new HashSet<>();
	
	//NO SE SABE SI VA
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario", orphanRemoval = true)
    private Set<Comentario> comentarios = new HashSet<>();
	
	//CONSTRUCTORS
	public Usuario() {
		super();
	}
	
	public Usuario(Long idUsuario, String nombreUsuario, String passwdUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.passwdUsuario = passwdUsuario;
	}
	

	//GETTERS AND SETTERS
	
	public Set<Comida> getComidas() {
		return comidas;
	}

	public void setComidas(Set<Comida> comidas) {
		this.comidas = comidas;
	}

	public boolean addComida(Comida comida) {
	    comida.addUsuario(this);
		return getComidas().add(comida);
	}
	
	public void eliminarComida(Comida comida) {
		this.comidas.remove(comida);
		comida.getUsuarios_comidas().remove(this);
	}
	
	
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPasswdUsuario() {
		return passwdUsuario;
	}

	public void setPasswdUsuario(String passwdUsuario) {
		this.passwdUsuario = passwdUsuario;
	}
	
	//GETTERS Y SETTERS DE ROLES DE USUARIO
	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	
	public boolean addRol(Rol rol) {
	    rol.addUsuario(this);
		return getRoles().add(rol);
	}

	public void eliminarRol(Rol rol) {
		this.roles.remove(rol);
		rol.getUsuarios().remove(this);
	}


	
}
