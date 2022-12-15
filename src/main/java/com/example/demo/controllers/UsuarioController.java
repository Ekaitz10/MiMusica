package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.services.PlaylistService;
import com.example.demo.services.UsuarioService;

@Controller
public class UsuarioController {
	
	UsuarioService usuarioService;
	PlaylistService playlistService;
	
	@Autowired
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	@Autowired
	public void setPlaylistService(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}
	
	
	//GetMapping creado por si se hace get del url para mostrar lo mismo que el post
	@GetMapping("/perfil")
	public ModelAndView getIndex() {
		ModelAndView m = new ModelAndView();
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication a = context.getAuthentication();
		String nombreusuario = a.getName();
		m.addObject("playlists", playlistService.todasLasPlaylists(usuarioService.buscarIdUsuario(nombreusuario)));
		m.addObject("usuario", usuarioService.buscarUsuario(nombreusuario));
		m.setViewName("perfilusuario");
		return m;
	}
}