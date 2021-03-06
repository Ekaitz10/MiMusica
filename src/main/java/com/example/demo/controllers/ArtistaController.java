package com.example.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.beans.Artista;
import com.example.demo.services.ArtistaService;

@Controller
public class ArtistaController {
	
	ArtistaService artistaService;
	
	@Autowired
	public void setArtistaService(ArtistaService artistaService) {
		this.artistaService = artistaService;
	}
	
	@PostMapping("/crearartista")
	public ModelAndView crearArtista(HttpSession session, @ModelAttribute Artista artista, RedirectAttributes redirectAttributes) {
		ModelAndView m = new ModelAndView();
		artistaService.crearArtista(artista);
		String nombreplaylist = (String) session.getAttribute("nombreplaylist");
		redirectAttributes.addAttribute("nombre", nombreplaylist);
		m.setViewName("redirect:perfil/playlist");
		return m;
	}
}