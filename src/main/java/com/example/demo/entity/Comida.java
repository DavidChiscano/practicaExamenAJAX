package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "comida")
public class Comida implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comida")
    private Long id_comida;
    private String nombre;
    private String pais;
    private String hiperenlace;
    private String descripcion;
    private Date fecha;
    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "usuario_comida", 
    joinColumns = @JoinColumn(name = "id_comida"), 
    inverseJoinColumns = @JoinColumn(name = "id_usuario"))
    private Set<Usuario> usuarios_comidas = new HashSet<>();
    
    
    
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "comida", orphanRemoval = true)
    private Set<Comentario> comentarios = new HashSet<>();
    
    
    
    public Set<Comentario> getComentarios() {
		return comentarios;
	}


   
	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	
	public void addComentario(Comentario comentario) {
		this.comentarios.add(comentario);
		getComentarios().add(comentario);
	}
	public void eliminarComentario(Comentario comentario) {
		this.comentarios.remove(comentario);
	}
	

	public Comida() {
        super();

    }


	public Comida(String nombre, String pais, String hiperenlace, String descripcion) {
		super();
		this.nombre = nombre;
		this.pais = pais;
		this.hiperenlace = hiperenlace;
		this.descripcion = descripcion;
	}


    public Comida(long id, String pais) {
    	super();
		this.id_comida = id;
		this.pais = pais;
	}



	public Set<Usuario> getUsuarios_comidas() {
		return usuarios_comidas;
	}

	public void setUsuarios_comidas(Set<Usuario> usuarios_comidas) {
		this.usuarios_comidas = usuarios_comidas;
	}

	
	public void addUsuario(Usuario usuario) {
		this.usuarios_comidas.add(usuario);
		usuario.getComidas().add(this);
	}
	public void eliminarComida(Usuario usuario) {
		this.usuarios_comidas.remove(usuario);
	}
	
	
	public Long getId_comida() {
        return id_comida;
    }

    public void setId_comida(Long id_comida) {
        this.id_comida = id_comida;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getHiperenlace() {
        return hiperenlace;
    }

    public void setHiperenlace(String hiperenlace) {
        this.hiperenlace = hiperenlace;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


}