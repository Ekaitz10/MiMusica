package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Cancion;
import com.example.demo.beans.Playlist;
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
		//si la playlist no existe, crear el usuario
				if(gestor.existePlaylist(playlist.getNombre()) == false) {
					gestor.crearPlaylist(playlist);
				}
		
	}
	
	public Playlist buscarPlaylist(String nombre) {
		return gestor.buscarPlaylist(nombre);
	}
	
	@Override
	public List<Playlist> todasLasPlaylists(int id) {
		return gestor.todasLasPlaylists(id);
	}

	@Override
	public void anadirCancion(int playlistid, int cancionid) {
		gestor.anadirCancion(playlistid, cancionid);
		
	}
	public int buscarIdPlaylist(String nombre) {
		return gestor.buscarIdPlaylist(nombre);
	}
	@Override
	public void eliminarPlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		
	}

}
