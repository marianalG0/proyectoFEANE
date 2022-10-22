package com.spring.boot.newapp.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.boot.newapp.model.Menu;
import com.spring.boot.newapp.repository.MenuRepository;
import com.spring.boot.newapp.service.IMenuService;

@Service

public class MenuServicejpa implements IMenuService {

	@Autowired
	private MenuRepository menuRepo;
	
	@Override
	public List<Menu> buscarTodo() {
		// TODO Auto-generated method stub
		return menuRepo.findAll();
	}

	@Override
	public Menu buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Menu> optional = menuRepo.findById(id);
		if(optional.isPresent()) {
		  return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Menu menu) {
		// TODO Auto-generated method stub
		menuRepo.save(menu);

	}

	@Override
	public List<Menu> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		menuRepo.deleteById(id);

	}

	@Override
	public List<Menu> buscarByExample(Example<Menu> example) {
		// TODO Auto-generated method stub
		return menuRepo.findAll(example);
	}

	@Override
	public Page<Menu> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return menuRepo.findAll(page);
	}

}
