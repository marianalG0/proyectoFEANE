package com.spring.boot.newapp.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.boot.newapp.model.Categoria;


@Service
public class CategoriasServiceImpl implements ICategoriasService{

	private List<Categoria> lista = null;
	
	//Declarando un constructor para crear la lista
		//Recuerda que el constructor solo se ejecuta una vez cada que se crea una instancia
	public CategoriasServiceImpl() {
		lista = new LinkedList<Categoria>();
		
		// Creamos algunas Categorias para poblar la lista ...
		
		// Categoria 1
		Categoria cat1 = new Categoria();
		cat1.setId(1);
		cat1.setNombre("Pasta");
		cat1.setDescripcion("Deliciosass pastas de diferentes ingredientes y tipos");
		
		// Categoria 2
		Categoria cat2 = new Categoria();
		cat2.setId(2);
		cat2.setNombre("Pizza");
		cat2.setDescripcion("Pizza de diferentes ingredientes");
		
					
		// Categoria 3
		Categoria cat3 = new Categoria();
		cat3.setId(3);
		cat3.setNombre("Hamburguesas");
		cat3.setDescripcion("deliciosas hamburguesas de diferente tamaño ");
		
		// Categoria 4
		Categoria cat4 = new Categoria();
		cat4.setId(4);
		cat4.setNombre("Vegana");
		cat4.setDescripcion("Comida saludable especialmente para personas veganas");
		
		// Categoria 5
		Categoria cat5 = new Categoria();
		cat5.setId(5);
		cat5.setNombre("Caldos");
		cat5.setDescripcion("Caldos recien preparados y calientitos");
		
		// Categoria 6
		Categoria cat6 = new Categoria();
		cat6.setId(6);
		cat6.setNombre("Vegetariana");
		cat6.setDescripcion("Comida saludable especialmente para personas vegetarianas");
		
		/**
		 * Agregamos los 5 objetos de tipo Categoria a la lista ...
		 */
		lista.add(cat1);			
		lista.add(cat2);
		lista.add(cat3);
		lista.add(cat4);
		lista.add(cat5);
		lista.add(cat6);

	}
	
	public void guardar(Categoria categoria) {	
		// mandamos a llamar nuestra lista para guardar 
		lista.add(categoria);
	}

	//Vamos a regresar la lista
	public List<Categoria> buscarTodas() {
		return lista;
	}

	//Va a recorrer la lista y comparara el id de cada objeto d tip categoria y cuando encuentre
		//un id que sea igual al que se pasa x parametro, regresara el objeto
	public Categoria buscarPorId(Integer idCategoria) {			
		for (Categoria cat : lista) {
			if (cat.getId()==idCategoria) {
				return cat;
			}
		}		
		return null;	
	}

	@Override
	public void eliminar(Integer idCategoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}


}
