package com.spring.boot.newapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.spring.boot.newapp.model.Menu;
import com.spring.boot.newapp.service.ICategoriasService;
import com.spring.boot.newapp.service.IMenuService;
import com.spring.boot.newapp.util.Utileria;



@Controller
@RequestMapping("/menu")
public class menuController {

	@Value("${feaneapp.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private IMenuService serviceMenu; /*Inyectar las clases de servicio en nuestro controlador*/
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Menu> lista = serviceMenu.buscarTodo();
    	model.addAttribute("menu", lista);
		return "menu/listMenu";
	}
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Menu> lista = serviceMenu.buscarTodas(page); // en lugar de que lo guarde en una lista lo guarda en una variable que implementa la interfaz pages 
	model.addAttribute("menus", lista);
	return "menu/listMenu";
	}
	
	@GetMapping("/create")
	public String crear(Menu menu, Model model) {
		return "menu/formMenu";
	}
	
	@PostMapping("/save")
	public String guardar(Menu menu, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart, Model model) {
		if (result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}			
			model.addAttribute("categorias", serviceCategorias.buscarTodas()); 
			return "menu/formMenu";
		}
		
		if(!multiPart.isEmpty()) {
			//String ruta = "c:/empleos/img-vacantes/";
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			//String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if(nombreImagen !=null) {
				//procesamos la variable nombreImagenO
				menu.setImagen(nombreImagen);
			}
		}
		
		serviceMenu.guardar(menu);
		attributes.addFlashAttribute("msg", "Registro Guardado");		
		System.out.println("Menu: " + menu);		
		return "redirect:/menu/indexPaginate";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idComida, RedirectAttributes attributes) {
		System.out.println("Borrando Comida con id: " + idComida); 
		serviceMenu.eliminar(idComida); 
		attributes.addFlashAttribute("msg", "La Comida fue eliminada!");
		return "redirect:/menu/indexPaginate"; 
	}
	@GetMapping("/edit/{id}")
	private String editar(@PathVariable("id") int idMenu, Model model) {
		Menu menu = serviceMenu.buscarPorId(idMenu);
		model.addAttribute("menu", menu);
		return "menu/formMenu"; 
	}
	
	/*@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idmenu, Model model) {
		
		Menu menu = serviceMenu.buscarPorId(idmenu);
	
		System.out.println("Comida: " + menu);
		model.addAttribute("comida", menu);
		return "detalle";
		
	}*/
	@ModelAttribute // para que lo pudamos usar en cualquier metodo
	public void setGenericos(Model model) {
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
	}
	@InitBinder 
	public void initBinder(WebDataBinder webDataBinder) {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
