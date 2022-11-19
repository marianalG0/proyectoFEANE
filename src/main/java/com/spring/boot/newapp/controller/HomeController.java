package com.spring.boot.newapp.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.boot.newapp.model.Menu;
import com.spring.boot.newapp.model.Perfil;
import com.spring.boot.newapp.model.Usuario;
import com.spring.boot.newapp.service.ICategoriasService;
import com.spring.boot.newapp.service.IMenuService;
import com.spring.boot.newapp.service.IUsuariosService;


@Controller
public class HomeController {
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
	@Autowired
    private IUsuariosService serviceUsuarios;
	
	@Autowired
	private IMenuService serviceMenu;
	 
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Menu> lista = serviceMenu.buscarTodo();
		model.addAttribute("menus", lista);
		
		return "tabla";
	}
	@GetMapping("/detalle")
	public String mostrarDetalle(Model model) {
		Menu menu = new Menu(); 
		
		model.addAttribute("menu", menu);
		return "detalle";
	}

	
	//Encargado de renderizar la pagina principal
		@GetMapping("/") 
		public String mostrarHome(Model model) {
			List<Menu> lista = serviceMenu.buscarEstatus();
			model.addAttribute("menus", lista);
			return "home";

		} 
		
		//Renderiza la vista de acerca
		@GetMapping("/acerca") 
		public String mostrarabout(Model model) {
			return "sobre";
		} 
		
		//Renderiza la vista de la cartelera
		@GetMapping("/cartmenu") 
		public String mostrarmenu(Model model) {
			List<Menu> lista = serviceMenu.buscarEstatus();
			model.addAttribute("menus", lista);
			return "carteleraMenu";

		} 
		
		//Renderiza la vista de descuentos
		@GetMapping("/descuentos") 
		public String mostrardesc(Model model) {
			List<Menu> lista = serviceMenu.buscarOfertas();
			model.addAttribute("menus", lista);
			return "descuentos";
		} 
		@GetMapping("/login" )
		public String mostrarLogin() {
		return "formLogin";
		}
		@GetMapping("/logout")
		public String logout(HttpServletRequest request){
		SecurityContextLogoutHandler logoutHandler =
		new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/login";
		}
		@GetMapping("/bcrypt/{texto}")
		@ResponseBody // sirve para que se renderize el texto y no una vista con el nombre
		public String encriptar(@PathVariable("texto") String texto) {
			return texto + " Encriptado en bcrypt: "+ passwordEncoder.encode(texto);
		}
		
		@GetMapping("/index")
		public String mostrarIndex(Authentication auth, HttpSession session) {
			String username = auth.getName();
			System.out.println("Nombre del usuario: " + username);
			
			for (GrantedAuthority rol: auth.getAuthorities()) { // es para que muestre que rol tiene
				System.out.println("ROL: "+ rol.getAuthority());
			}
			if (session.getAttribute("usuario") == null) {
				Usuario usuario = serviceUsuarios.buscarPorUsername(username);
				usuario.setPassword(null); // para que no guarde la contraseña o no lo muestre
				System.out.println("Usuario: " + usuario);
				// para buscar datos en la sesion
				session.setAttribute("usuario", usuario);
			}
			
			return "redirect:/";
		}
		@GetMapping("/signup")
		public String registrarse(Usuario usuario) {
			return "usuarios/formRegistro"; 
		}
		//Ejercicio
		@PostMapping("/signup")
		public String guardarRegistro(Usuario usuario, BindingResult result, RedirectAttributes attributes) {
			 String pwdPlano = usuario.getPassword();
			 String pwdEncriptado = passwordEncoder.encode(pwdPlano);
			 usuario.setPassword(pwdEncriptado);
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
			return "redirect:/";
		}
		
		/**@PostMapping("/save")
		public String guardar(Compras compras, BindingResult result, RedirectAttributes attributes,
				@RequestParam("archivoImagen") MultipartFile multiPart, Model model) {
			
			serviceCompra.guardar(compras);
			attributes.addFlashAttribute("msg", "Registro Guardado");		
			System.out.println("Menu: " + compras);		
			return "redirect:/menu/indexPaginate";
		}*/
		@GetMapping("/search")
		public String buscar(@ModelAttribute("search") Menu menu, Model model) {
			System.out.println("buscando por" + menu);
		
			ExampleMatcher matcher = ExampleMatcher.
			// where descripcion like '%?%'
				    matching().withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());

			Example<Menu> example = Example.of(menu, matcher);
			List<Menu> lista = serviceMenu.buscarByExample(example);
			model.addAttribute("menus", lista);
			return "home";
			
			
		}
		
		@ModelAttribute
		public void setGenericos(Model model) {
			Menu menuSearch = new Menu();
			menuSearch.reset();

		 	model.addAttribute("menu", serviceMenu.buscarOfertas());
		 	model.addAttribute("menu", serviceMenu.buscarEstatus());
			model.addAttribute("categorias", serviceCategorias.buscarTodas());
		    model.addAttribute("search", menuSearch);
		


		
		}
		// FIN DE LOS USUARIOS
		@InitBinder
		public void initBinder(WebDataBinder binder) {
			binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		}
		
		

}

