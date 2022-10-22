package com.spring.boot.newapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.boot.newapp.model.Menu;
import com.spring.boot.newapp.service.IMenuService;
import com.spring.boot.newapp.util.Utileria;



@Controller
@RequestMapping("/menu")
public class menuController {

	@Value("${feaneapp.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private IMenuService serviceMenu; /*Inyectar las clases de servicio en nuestro controlador*/
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Menu> lista = serviceMenu.buscarTodo();
    	model.addAttribute("menu", lista);
		return "menu/listMenu";
	}
	
	@GetMapping("/create")
	public String crear(Menu menu, Model model) {
		return "menu/formMenu";
	}
	
	@PostMapping("/save")
	public String guardar(Menu menu, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart) {
		if (result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
			}			
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
		System.out.println("Vacante: " + menu);		
		return "redirect:/menu/index";
	}
	
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idComida, Model model) {
		System.out.println("Borrando la comida con id: " + idComida);
		model.addAttribute("id", idComida);

		return "mensaje";

	}
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idmenu, Model model) {
		
		Menu menu = serviceMenu.buscarPorId(idmenu);
	
		System.out.println("Comida: " + menu);
		model.addAttribute("comida", menu);
		return "detalle";
		
	}
}
