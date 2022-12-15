package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.beans.SecurityUser;
import com.example.demo.beans.Usuario;
import com.example.demo.security.UserDetailsManager;
import com.example.demo.services.ArtistaService;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.UsuarioService;

@Controller
public class IndexController {
	
	PlaylistService playlistService;
	UsuarioService usuarioService;
	ArtistaService artistaService;
	
	@Autowired
	public void setPlaylistService(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}
	
	@Autowired
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@Autowired
	public void setArtistaService(ArtistaService artistaService) {
		this.artistaService = artistaService;
	}
	
	@Autowired
	private UserDetailsManager userDetailsManager;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/")
	public String getHome() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication a = context.getAuthentication();
		if(a.isAuthenticated()){
			return "redirect:/perfil";
		}else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/logout")
	public String getLoginOut() {
		return "logout";
	}
	
	//vista login
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	//accede a la vista principal
	@GetMapping("/register")
	public ModelAndView index() {
		ModelAndView m = new ModelAndView();
		m.addObject("usuario", new Usuario());
		m.setViewName("register");
		return m;
	}
	//crea usuario y devuelve la vista principal
	@PostMapping("/register")
	public ModelAndView crearUsuario(@ModelAttribute("usuario") Usuario usuario) {
		System.err.println(usuario);
		String password = bCryptPasswordEncoder.encode(usuario.getPassword());
		String role = "USER";
		userDetailsManager.addUser(usuario.getNombre(), usuario.getApellido(),usuario.getNacionalidad(), usuario.getEdad(), usuario.getUsername(), password, role);
		ModelAndView m = new ModelAndView();
		m.setViewName("login");
		return m;
	}
	
	@GetMapping("/addUser")
	public ModelAndView addUser() {
		String password = bCryptPasswordEncoder.encode("1234");
		String role = "USER";
		userDetailsManager.addUser("Unai", "Iriarte","Somalia",21, "IriM", password, role);
		ModelAndView m = new ModelAndView();
		m.setViewName("login");
		return m;
	}
	
	//ADMINISTRAR CONTENIDO
	@GetMapping("/admin/contenido")
	public ModelAndView administrarContenido() {
		ModelAndView m = new ModelAndView();
		m.addObject("artistas", artistaService.todosLosArtistas());
		m.setViewName("administrarcontenido");
		return m;
	}
	//ADMINISTRAR USUARIOS
	@GetMapping("/admin/usuarios")
	public ModelAndView administrarUsuarios() {
		ModelAndView m = new ModelAndView();
		List<Usuario> usuarios = usuarioService.todosLosUsuarios();
		String nombreuser = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario currentUser = usuarioService.buscarUsuario(nombreuser);
		usuarios.remove(currentUser);
		m.addObject("usuarios", usuarios);
		m.setViewName("administrarusuarios");
		return m;
	}
	
	@PostMapping("/usuario/borrar")
	public String borrarUser(@RequestParam("id") long Id) {
		userDetailsManager.delete(Id);
		return "redirect:/admin/usuarios";
	}
	
	@PostMapping("/admin/user/update")
	public String updateUser(@RequestParam("role") String role, @RequestParam("username") String username) {
		Usuario usuario = usuarioService.buscarUsuario(username);
		usuarioService.updateUser(usuario, role);
		return "redirect:/admin/usuarios";
	}
	}