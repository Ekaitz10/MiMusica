package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.beans.Cancion;
import com.example.demo.services.ArtistaService;
import com.example.demo.services.CancionService;
import com.example.demo.services.PlaylistService;

@Controller
public class CancionController {
	CancionService cancionService;
	ArtistaService artistaService;
	PlaylistService playlistService;
	
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
	
	@PostMapping("/crearcancion")
	public ModelAndView crearCancion(HttpSession session, HttpServletRequest request, @ModelAttribute Cancion cancion, RedirectAttributes redirectAttributes) {
		ModelAndView m = new ModelAndView();
		String nombreartista = request.getParameter("artista");
		cancion.setArtista_id(artistaService.buscarIdArtista(nombreartista));
		cancionService.crearCancion(cancion);
		String nombreplaylist = (String) session.getAttribute("nombreplaylist");
		redirectAttributes.addAttribute("nombre", nombreplaylist);
		m.setViewName("redirect:perfil/playlist");
		return m;
	}
	
	@PostMapping("/crearcancionplaylist")
	public ModelAndView crearRelacion(HttpSession session, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		ModelAndView m = new ModelAndView();
		String nombreplaylist = (String) session.getAttribute("nombreplaylist");
		int playlistid = playlistService.buscarIdPlaylist(nombreplaylist);
		String nombrecancion = request.getParameter("titulo");
		int cancionid = cancionService.buscarIdCancion(nombrecancion);
		playlistService.anadirCancion(playlistid, cancionid);
		redirectAttributes.addAttribute("nombre", nombreplaylist);
		m.setViewName("redirect:perfil/playlist");
		return m;
	}
}
