package com.spring.boot.newapp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="menu")
public class Menu {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	private String nombre;
	private String descripcion;
	private Date fecha; /*Se le importa su extension*/
	private Double precio;
	private Integer descuento;
	private Integer oferta;
	private String imagen="no-image.png";
	private String estatus;
	private String detalles; 
	//@Transient // se ignora el mapeo de categoria
	@OneToOne // representa la relacion uno a uno (1:1)
	@JoinColumn(name="idCategoria")
	private Categoria categoria;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getDescuento() {
		return descuento;
	}
	public void setDescuento(Integer descuento) {
		this.descuento = descuento;
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
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public void reset() {
		this.imagen=null;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", precio=" + precio + ", descuento=" + descuento + ", oferta=" + oferta + ", imagen=" + imagen
				+ ", estatus=" + estatus + ", detalles=" + detalles + ", categoria=" + categoria + "]";
	}
	
	

}