package com.spring.boot.newapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "perfiles")//Mapeando la clase a la tabla perfiles
public class Perfiles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private Integer idperfiles;
	private String perfiles;
	
	public Integer getIdperfiles() {
		return idperfiles;
	}
	public void setIdperfiles(Integer idperfiles) {
		this.idperfiles = idperfiles;
	}
	public String getPerfiles() {
		return perfiles;
	}
	public void setPerfiles(String perfiles) {
		this.perfiles = perfiles;
	}
	@Override
	public String toString() {
		return "Perfiles [idperfiles=" + idperfiles + ", perfiles=" + perfiles + "]";
	}





}
