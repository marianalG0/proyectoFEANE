package com.spring.boot.newapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.boot.newapp.model.Menu;
import com.spring.boot.newapp.service.IMenuService;


@Controller
public class HomeController {
	
	@Autowired
	private IMenuService serviceMenu;
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Menu> lista = serviceMenu.buscarTodo();
		model.addAttribute("comidas", lista);
		
		return "tabla";
	}
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Menu menu = new Menu(); 
		menu.setNombre("Deliciosa hambuerguesa");
		menu.setDescripcion("Veniam debitis quaerat officiis quasi cupiditate quo, quisquam velit,");
		menu.setPrecio(80.0);
		menu.setDescuento("16%");
		model.addAttribute("comida", menu);
		return "detalle";
	}

	
	//Encargado de renderizar la pagina principal
		@GetMapping("/") 
		public String mostrarHome(Model model) {
			List<Menu> lista = serviceMenu.buscarTodo();
			model.addAttribute("comidas", lista);
			return "home";

		} 
		
		//Encargado de renderizar la pagina principal
		@GetMapping("/about") 
		public String mostrarabout(Model model) {
			return "about";
		} 
		//Encargado de renderizar la pagina principal
		@GetMapping("/book") 
		public String mostrarbook(Model model) {
			return "book";

		}
		
		@GetMapping("/menu") 
		public String mostrarmenu(Model model) {
			List<Menu> lista = serviceMenu.buscarTodo();
			model.addAttribute("comidas", lista);
			return "menu";

		} 
		

		@GetMapping("/descuentos") 
		public String mostrardesc(Model model) {
			List<Menu> lista = serviceMenu.buscarTodo();
			model.addAttribute("comidas", lista);
			return "descuentos";

		} 
		
		
		

}

