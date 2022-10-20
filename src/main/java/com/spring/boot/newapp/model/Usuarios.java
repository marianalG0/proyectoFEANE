/**
 * Esta clase representa una entidad (un registro) en la tabla de Usuarios de la base de datos
 */
package com.spring.boot.newapp.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")//Mapeando la clase con la tabla usuarios
public class Usuarios {
	//ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private Integer idusuarios;
	private String username;
	private String nombre;
	private String email;
	private String password;
	private Integer estatus;	
	private Date fechaRegistro;
	//Configurando la relacion muchos a muchos
	@ManyToMany(fetch=FetchType.EAGER)//Anotacion manyTOmany, especificamos la configuracion de relacion, pasamos como parametro eager, especificamos que en la consulta se recupere todos los perfiles que tenga asociado 
	@JoinTable(name="usuarioperfil",//nombre de la tabla intermedia
			   joinColumns = @JoinColumn(name="idusuarios"),//Parametro que tiene como valor la anotacion en la que escribimos el parametro name con valor de la llave foranea
			   inverseJoinColumns = @JoinColumn(name="idperfiles")//especificamos la otra llave foranea		
			)//Anotacion que configura la tabla intermedia
	private List<Perfiles> perfiles;//atributo de Lista de tipo perfil
	
	//Metodo que ayuda a agregar perfiles a la lista y posteriormente la lista que se guarde
	public void agregar(Perfiles tempPerfil) {//Parametro objeto de tipo perfil
		if (perfiles == null) {//Condicional en el que compara si perfil es igual a nulo, entonces ->
			perfiles = new LinkedList<Perfiles>();//Se crea una nueva lista
		}
		perfiles.add(tempPerfil);//Se agrega el perfil
	}

	

	public Integer getIdusuarios() {
		return idusuarios;
	}



	public void setIdusuarios(Integer idusuarios) {
		this.idusuarios = idusuarios;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
		

	public List<Perfiles> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfiles> perfiles) {
		this.perfiles = perfiles;
	}


	@Override
	public String toString() {
		return "Usuarios [idusuarios=" + idusuarios + ", username=" + username + ", nombre=" + nombre + ", email="
				+ email + ", password=" + password + ", estatus=" + estatus + ", fechaRegistro=" + fechaRegistro
				+ ", perfiles=" + perfiles + "]";
	}

	
}
