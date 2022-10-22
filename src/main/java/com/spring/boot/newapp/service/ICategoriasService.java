package com.spring.boot.newapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.boot.newapp.model.Categoria;



public interface ICategoriasService {
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);	

	// Ejercicio: Implementar método
		void eliminar(Integer idCategoria);
		
		Page<Categoria>buscarTodas(Pageable page);

}

/*
 *1. Crear la clase categoriasServiceImpl que implemente esta interfaz (guardar las categorias en una lista (linkedlist)) 
 *2. Inyectar la clase de servicio en categoriascontroller 
 *3. Completar los siguientes metodos en categoria controller 
 	- mostrarIndex: renderizar el listado de categorias, configurar la url del boton para crear categoria
 	- guardar: guardar el objeto categoria a traves de la clse de servicio, validar errores de databinding y mostrarlos el usuario en caso de haber
 					mostrar al usuario mensaje de confirmacion de registro guardado
 *4.Agregar un nuevo menu llamado categorias y cnfigurar la url al listado de categorias
 */


