package com.spring.boot.newapp.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.boot.newapp.model.Compras;
import com.spring.boot.newapp.model.Menu;
import com.spring.boot.newapp.model.Perfil;
import com.spring.boot.newapp.model.Usuario;
import com.spring.boot.newapp.service.IComprasService;
import com.spring.boot.newapp.service.IMenuService;
import com.spring.boot.newapp.service.IUsuariosService;


@Controller
public class HomeController {
	
	@Autowired
    private IUsuariosService serviceUsuarios;
	@Autowired
	private IMenuService serviceMenu;
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Menu> lista = serviceMenu.buscarTodo();
		model.addAttribute("menus", lista);
		
		return "tabla";
	}
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Menu menu = new Menu(); 
		menu.setNombre("Deliciosa hambuerguesa");
		menu.setDescripcion("Veniam debitis quaerat officiis quasi cupiditate quo, quisquam velit,");
		menu.setPrecio(80.0);
		menu.setDescuento(16);
		model.addAttribute("menu", menu);
		return "detalle";
	}

	
	//Encargado de renderizar la pagina principal
		@GetMapping("/") 
		public String mostrarHome(Model model) {
			List<Menu> lista = serviceMenu.buscarTodo();
			model.addAttribute("menus", lista);
			return "home";

		} 
		
		//Encargado de renderizar la pagina principal
		@GetMapping("/about") 
		public String mostrarabout(Model model) {
			return "sobre";
		} 
		//Encargado de renderizar la pagina principal
		@GetMapping("/book") 
		public String mostrarbook(Model model) {
			return "book";

		}
		
		@GetMapping("/menu") 
		public String mostrarmenu(Model model) {
			List<Menu> lista = serviceMenu.buscarTodo();
			model.addAttribute("menus", lista);
			return "carteleraMenu";

		} 
		

		@GetMapping("/descuentos") 
		public String mostrardesc(Model model) {
			List<Menu> lista = serviceMenu.buscarTodo();
			model.addAttribute("menus", lista);
			return "descuentos";

		} 
		@GetMapping("/signup")
		public String registrarse(Usuario usuario) {
			return "usuarios/formRegistro";
		}
		//Ejercicio
		@PostMapping("/signup")
		public String guardarRegistro(Usuario usuario, BindingResult result, RedirectAttributes attributes) {
			//Ejercicio.
			 usuario.setEstatus(1);
			 usuario.setFechaRegistro(new Date());
			 
			 Perfil perfil = new Perfil();
			 perfil.setId(3);
			 usuario.agregar(perfil);
			 //
			serviceUsuarios.guardar(usuario); 
			attributes.addFlashAttribute("msg", "Los datos se guardaron correctamente"); 	
			// Ejercicio realizar
			return "redirect:/usuarios/index";
		}
		
		/**@PostMapping("/save")
		public String guardar(Compras compras, BindingResult result, RedirectAttributes attributes,
				@RequestParam("archivoImagen") MultipartFile multiPart, Model model) {
			
			serviceCompra.guardar(compras);
			attributes.addFlashAttribute("msg", "Registro Guardado");		
			System.out.println("Menu: " + compras);		
			return "redirect:/menu/indexPaginate";
		}*/
		
		
		

}

