package com.example.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.beans.Playlist;
import com.example.demo.beans.Usuario;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.UsuarioService;

@Controller
public class PlaylistController {
	
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
	
	//crea una playlist y devuelve la vista /perfil
	@PostMapping("/crearplaylist")
	public ModelAndView index(HttpSession session,@ModelAttribute Playlist playlist) {
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		String nombreusuario = usuario.getNombre();
		ModelAndView m = new ModelAndView();
		playlist.setId_usuario(usuarioService.buscarIdUsuario(nombreusuario));
		playlistService.crearPlaylist(playlist);
		m.addObject("playlists", playlistService.todasLasPlaylists());
		m.setViewName("redirect:/perfil");
		return m;
	}

}