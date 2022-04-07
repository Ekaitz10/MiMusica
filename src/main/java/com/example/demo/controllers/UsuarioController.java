package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.beans.Usuario;
import com.example.demo.services.PlaylistService;

@Controller
public class UsuarioController {
	
	PlaylistService playlistService;
	
	@Autowired
	public void setUsuarioService(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}
	
	@GetMapping("/perfil")
	public ModelAndView index() {
		ModelAndView m = new ModelAndView();
		m.addObject("playlists", playlistService.todasLasPlaylists());
		m.setViewName("perfilusuario");
		return m;
	}
	
}