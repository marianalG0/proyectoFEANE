package com.spring.boot.newapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idcategorias;
	private String nombre;
	private String descripcion;



	public Integer getIdcategorias() {
		return idcategorias;
	}

	public void setIdcategorias(Integer idcategorias) {
		this.idcategorias = idcategorias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Categoria [idcategorias=" + idcategorias + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

	

}
