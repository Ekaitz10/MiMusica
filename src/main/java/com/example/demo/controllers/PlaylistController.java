package com.example.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.beans.Cancion;
import com.example.demo.beans.Playlist;
import com.example.demo.beans.Usuario;
import com.example.demo.services.ArtistaService;
import com.example.demo.services.CancionService;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.UsuarioService;

@Controller
public class PlaylistController {
	
	PlaylistService playlistService;
	UsuarioService usuarioService;
	CancionService cancionService;
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
	public void setCancionService(CancionService cancionService) {
		this.cancionService = cancionService;
	}
	@Autowired
	public void setArtistaService(ArtistaService artistaService) {
		this.artistaService = artistaService;
	}
	
	@GetMapping("/perfil/playlist")
	public ModelAndView devuelvePlaylist(HttpSession session, HttpServletRequest request) {
		String nombreplaylist = request.getParameter("nombre");
		ModelAndView m = new ModelAndView();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Playlist playlist = playlistService.buscarPlaylist(nombreplaylist, usuario);
		session.setAttribute("nombreplaylist", nombreplaylist);
		m.addObject("artistas", artistaService.todosLosArtistas());
		m.addObject("canciones", cancionService.todasLasCanciones());
		m.addObject("cancionesplaylist", playlist.getCanciones());
		m.addObject("playlist", playlist);
		m.setViewName("playlist");
		return m;
	}
	//crea una playlist y devuelve la vista /perfil
	@PostMapping("/crearplaylist")
	public ModelAndView creaPlaylist(HttpSession session,@ModelAttribute Playlist playlist) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		ModelAndView m = new ModelAndView();
		playlist.setUsuario(usuario);
		playlistService.crearPlaylist(playlist, usuario);
		m.setViewName("redirect:/perfil");
		return m;
	}

}