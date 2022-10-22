package com.spring.boot.newapp.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.spring.boot.newapp.model.Menu;


@Primary
public interface IMenuService {
	
	List<Menu> buscarTodo();
	Menu buscarPorId(Integer idmenu);
	
	void guardar(Menu menu);
	
	List<Menu> buscarDestacadas();
	
	void eliminar(Integer idmenu);
	
	List<Menu> buscarByExample(Example<Menu> example);
	
	Page<Menu>buscarTodas(Pageable page);
}
