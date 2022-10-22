package com.spring.boot.newapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.newapp.model.Categoria;
//import org.springframework.data.repository.CrudRepository;

//public interface CategoriasRepository extends CrudRepository<Categoria, Integer> { 
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
	
}

