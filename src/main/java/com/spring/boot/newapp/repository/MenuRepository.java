package com.spring.boot.newapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.newapp.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

	List<Menu> findByEstatus(String estatus); // el findby inicia con el nomnbre del metodo

	List<Menu> findByOfertaAndEstatusOrderByIdDesc(int oferta, String estatus);

	//List <Menu> findBySalarioBetweenOrderBySalarioDesc(double s1, double s2); // metodo para buscar un salario definido en cierto rango y que vaya en orden descendente 

	List<Menu> findByEstatusIn(String[] estatus);
}
