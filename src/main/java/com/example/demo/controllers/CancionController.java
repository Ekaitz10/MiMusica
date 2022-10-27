package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.beans.Cancion;
import com.example.demo.beans.Playlist;
import com.example.demo.beans.Usuario;
import com.example.demo.services.ArtistaService;
import com.example.demo.services.CancionService;
import com.example.demo.services.PlaylistCancionService;
import com.example.demo.services.PlaylistService;

@Controller
public class CancionController {
	CancionService cancionService;
	ArtistaService artistaService;
	PlaylistService playlistService;
	PlaylistCancionService playlistCancionService;
	
	@Autowired
	public void setCancionService(CancionService cancionService) {
		this.cancionService = cancionService;
	}
	
	@Autowired
	public void setArtistaService(ArtistaService artistaService) {
		this.artistaService = artistaService;
	}
	
	@Autowired
	public void setPlaylistService(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}
	
	@Autowired
	public void setPlaylistCancionService(PlaylistCancionService playlistCancionService) {
		this.playlistCancionService = playlistCancionService;
	}
	
	
	@PostMapping("/crearcancion")
	public ModelAndView crearCancion(HttpSession session, HttpServletRequest request, @RequestParam("titulo") String titulo, @RequestParam("artista") Long idArtista, RedirectAttributes redirectAttributes) {
		ModelAndView m = new ModelAndView();
		String nombreplaylist = (String) session.getAttribute("nombreplaylist");
		redirectAttributes.addAttribute("nombre", nombreplaylist);
		Cancion cancion = new Cancion();
		cancion.setTitulo(titulo);
		cancion.setArtista(artistaService.buscarArtista(idArtista));
		cancionService.crearCancion(cancion);
		m.setViewName("redirect:administrarcontenido");
		return m;
	}
	@PostMapping("/anadirCancion")
	public ModelAndView anadirCancion(@RequestParam("titulo") String titulo, @RequestParam("artista") Long artistaId,HttpSession session, RedirectAttributes redirectAttributes) {
		ModelAndView m = new ModelAndView();
		Cancion cancion = cancionService.buscarCancionPorTituloYArtista(titulo, artistaId);
		cancionService.crearCancion(cancion);
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		String nombreplaylist = (String) session.getAttribute("nombreplaylist");
		Playlist playlist = playlistService.buscarPlaylist(nombreplaylist, usuario);
		playlistCancionService.anadirCancion(playlist, cancion);
		redirectAttributes.addAttribute("nombre", nombreplaylist);
		m.setViewName("redirect:perfil/playlist");
		return m;
	}
}