package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.beans.Playlist;
import com.example.demo.beans.Usuario;
import com.example.demo.services.ArtistaService;
import com.example.demo.services.CancionService;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.UsuarioService;

@Controller
public class PlaylistController {
	
	@Autowired
	PlaylistService playlistService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	CancionService cancionService;
	@Autowired
	ArtistaService artistaService;
	
	@GetMapping("/perfil/playlist")
	public ModelAndView devuelvePlaylist(HttpSession session, HttpServletRequest request) {
		String nombreplaylist = request.getParameter("nombre");
		ModelAndView m = new ModelAndView();
		String nombreusuario = SecurityContextHolder.getContext().getAuthentication().getName();
		Playlist playlist = playlistService.buscarPlaylist(nombreplaylist, usuarioService.buscarUsuario(nombreusuario));
		session.setAttribute("nombreplaylist", nombreplaylist);
		m.addObject("artistas", artistaService.todosLosArtistas());
		m.addObject("cancionesplaylist", playlist.getCanciones());
		m.addObject("playlist", playlist);
		m.addObject("canciones", cancionService.todasLasCanciones());
		m.setViewName("playlist");
		return m;
	}
	//crea una playlist y devuelve la vista /perfil
	@PostMapping("/crearplaylist")
	public ModelAndView creaPlaylist(@ModelAttribute Playlist playlist) {
		String nombreusuario = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioService.buscarUsuario(nombreusuario);
		ModelAndView m = new ModelAndView();
		playlist.setUsuario(usuario);
		playlistService.crearPlaylist(playlist, usuario);
		m.setViewName("redirect:/perfil");
		return m;
	}

}