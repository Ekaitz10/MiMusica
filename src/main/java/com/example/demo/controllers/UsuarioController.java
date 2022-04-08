package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.beans.Playlist;
import com.example.demo.beans.Usuario;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.UsuarioService;

@Controller
public class UsuarioController {
	
	PlaylistService playlistService;
	UsuarioService usuarioService;
	
	@Autowired
	public void setPlaylistService(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}
	@Autowired
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/perfil")
	public ModelAndView mostrarUseryPlaylists(@SessionAttribute Usuario usuario, @RequestParam("nombre") String nombre) {
		ModelAndView m = new ModelAndView();
		m.addObject("usuario", usuario);
		m.addObject("playlists", playlistService.todasLasPlaylists());
		m.setViewName("perfilusuario");
		return m;
	}
	@PostMapping("/perfil")
	public ModelAndView crearPlaylist(@ModelAttribute Playlist playlist) {		
		ModelAndView m = new ModelAndView();
		playlistService.crearPlaylist(playlist);
		m.setViewName("redirect:/perfil");
		return m;
	}
	
}