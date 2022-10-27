package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.beans.Usuario;
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
	
	
	//accede a la vista principal
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView m = new ModelAndView();
		m.addObject("usuarios", usuarioService.todosLosUsuarios());
		m.setViewName("index");
		return m;
	}
	//crea usuario y devuelve la vista principal
	@PostMapping("/")
	public ModelAndView crearUsuario(@ModelAttribute Usuario usuario) {
		System.out.println(usuario);
		usuarioService.crearUsuario(usuario);
		ModelAndView m = new ModelAndView();
		m.addObject("usuarios", usuarioService.todosLosUsuarios());
		m.setViewName("index");
		return m;
	}
	
	//ADMINISTRAR CONTENIDO
	@GetMapping("/administrarcontenido")
	public ModelAndView administrarContenido() {
		ModelAndView m = new ModelAndView();
		m.addObject("artistas", artistaService.todosLosArtistas());
		m.setViewName("administrarcontenido");
		return m;
	}
}