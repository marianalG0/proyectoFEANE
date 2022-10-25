package com.spring.boot.newapp.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.spring.boot.newapp.model.Compras;
import com.spring.boot.newapp.repository.ComprasRepository;
import com.spring.boot.newapp.service.IComprasService;

public class ComprasServicejpa implements IComprasService{

	@Autowired
	private ComprasRepository comprasRepo;
	
	@Override
	public List<Compras> buscarTodo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compras buscarPorId(Integer idCompra) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardar(Compras compras) {
		// TODO Auto-generated method stub
		comprasRepo.save(compras);
	}

	@Override
	public List<Compras> buscarOfertas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Integer idCompra) {
		// TODO Auto-generated method stub
		comprasRepo.deleteById(idCompra);
		
	}

	@Override
	public List<Compras> buscarByExample(Example<Compras> example) {
		// TODO Auto-generated method stub
		return null;
	}

}
