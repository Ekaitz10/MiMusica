package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Cancion;
import com.example.demo.beans.Playlist;
import com.example.demo.beans.Usuario;
import com.example.demo.db.GestorDB;
import com.example.demo.interfaces.IPlaylistService;

@Service
public class PlaylistService implements IPlaylistService{

	GestorDB gestor;
	
	@Autowired
	public PlaylistService(GestorDB gestor) {
		this.gestor = gestor;
	}
	
	@Override
	public void crearPlaylist(Playlist playlist) {
		gestor.crearPlaylist(playlist);
		
	}

	@Override
	public List<Playlist> todasLasPlaylists() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Playlist> todasLasPlaylists(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void anadirCancion(Cancion cancion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarPlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		
	}

}
