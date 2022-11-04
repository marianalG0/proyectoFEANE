package com.spring.boot.newapp.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.boot.newapp.model.Categoria;
import com.spring.boot.newapp.service.ICategoriasService;




@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {
	
	@Autowired
	//@Qualifier("categoriaServicejpa")  //Le pasamos como parametro el nombre de la implememtacion que se va a inyectar en el controlador 
   	private ICategoriasService serviceCategorias;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Categoria> lista = serviceCategorias.buscarTodas();
    	model.addAttribute("categorias", lista);
		return "categorias/listCategorias";		
	}
	
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Categoria>lista = serviceCategorias.buscarTodas(page);
	model.addAttribute("categorias", lista);
	return "categorias/listCategorias";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String crear(Categoria categoria) {
		return "categorias/formCategoria";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()){		
			System.out.println("Existieron errores, por favor corrijalo");
			return "categorias/formCategoria";
		}	
		
		// Guadamos el objeto categoria en la bd
		serviceCategorias.guardar(categoria);
		attributes.addFlashAttribute("msg", "Los datos de la categoría fueron guardados!");		
		return "redirect:/categorias/indexPaginate";
	}
	
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable ("id") int idCategoria, RedirectAttributes attributes) {
		
		try {
			System.out.println("Borrando Categoria con id: " + idCategoria);
			serviceCategorias.eliminar(idCategoria);//Ya estamos eliminando en base de datos
			attributes.addFlashAttribute("msg","La categoria fue eliminada");
			//model.addAttribute("id",idVacante); //No agregamos id al modelo por eso se elimina
		}catch(Exception e){
			attributes.addFlashAttribute("msg","La categoria esta asignada a un vacante, no es posible eliminarse");
		}
		
		return "redirect:/categorias/index";
	}
	
	//Metodo que va a buscar por id el objeto y lo enviara al formulario
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable ("id") int idCategoria, Model model ){
		Categoria categoria = serviceCategorias.buscarPorId(idCategoria);
		model.addAttribute("categoria", categoria);
		
		return "categorias/formCategoria";
	}
	
	//Agregando datos genericos o comunes para los metodos de este controlador
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("categorias", serviceCategorias.buscarTodas() );
	}
}
