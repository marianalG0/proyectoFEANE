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
@Primary
public class MenuServicejpa implements IMenuService {

	@Autowired
	private MenuRepository menuRepo;
	
	@Override
	public List<Menu> buscarTodo() {
		return menuRepo.findAll();
	}

	@Override
	public Menu buscarPorId(Integer idmenu) {
		Optional<Menu> optional = menuRepo.findById(idmenu);
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
	public List<Menu> buscarOfertas() {
		return menuRepo.findByOfertaAndEstatusOrderByIdDesc(1, "Aprobada");
	}

	@Override
	public void eliminar(Integer idmenu) {
		menuRepo.deleteById(idmenu);

	}

	@Override
	public List<Menu> buscarByExample(Example<Menu> example) {
		return menuRepo.findAll(example);
	}

	@Override
	public Page<Menu> buscarTodas(Pageable page) {
		return menuRepo.findAll(page);
	}
	

}
