package com.spring.boot.newapp.service;

import java.util.List;

import org.springframework.data.domain.Example;

import com.spring.boot.newapp.model.Compras;


public interface IComprasService {


	List<Compras> buscarTodo();
	Compras buscarPorId(Integer idCompra);
	
	void guardar(Compras compras);
	
	List<Compras> buscarOfertas();
	
	void eliminar(Integer idCompra);
	
	List<Compras> buscarByExample(Example<Compras> example);
}
