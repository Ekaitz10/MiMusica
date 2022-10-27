package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.beans.Usuario;
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
	
	//accede a la vista principal del usuario donde puede ver y crear Playlists
	@PostMapping("/perfil")
	public ModelAndView postIndex(HttpServletRequest request, HttpSession session) {
		ModelAndView m = new ModelAndView();
		String nombreusuario = request.getParameter("nombre");
		Usuario usuario = usuarioService.buscarUsuario(nombreusuario);
		session.setAttribute("usuario", usuario);
		m.addObject("playlists", playlistService.todasLasPlaylists(usuarioService.buscarIdUsuario(nombreusuario)));
		m.setViewName("perfilusuario");
		return m;
	}
	
	//GetMapping creado por si se hace get del url para mostrar lo mismo que el post
	@GetMapping("/perfil")
	public ModelAndView getIndex(HttpSession session) {
		ModelAndView m = new ModelAndView();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		String nombreusuario = usuario.getNombreUsuario();
		m.addObject("playlists", playlistService.todasLasPlaylists(usuarioService.buscarIdUsuario(nombreusuario)));
		m.setViewName("perfilusuario");
		return m;
	}
}