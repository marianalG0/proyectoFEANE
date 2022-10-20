package com.spring.boot.newapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class menu {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idmenu;
	private String nombre;
	private String descripcion;
	private String detalles;
	private String descuento;
	private Integer idcategoria;
	private Double precio;
	private Integer oferta;
	private String imagen="no-image.png";
	
	
	public Integer getIdmenu() {
		return idmenu;
	}
	public void setIdmenu(Integer idmenu) {
		this.idmenu = idmenu;
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
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	public String getDescuento() {
		return descuento;
	}
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	public Integer getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(Integer idcategoria) {
		this.idcategoria = idcategoria;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getOferta() {
		return oferta;
	}
	public void setOferta(Integer oferta) {
		this.oferta = oferta;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	@Override
	public String toString() {
		return "menu [idmenu=" + idmenu + ", nombre=" + nombre + ", descripcion=" + descripcion + ", detalles="
				+ detalles + ", descuento=" + descuento + ", idcategoria=" + idcategoria + ", precio=" + precio
				+ ", oferta=" + oferta + ", imagen=" + imagen + "]";
	}
	
	
}