package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Cancion;
import com.example.demo.beans.CancionPlaylist;
import com.example.demo.beans.Playlist;
import com.example.demo.interfaces.IPlaylistCancionService;
import com.example.demo.repositorios.PlaylistCancionRepository;

@Service
public class PlaylistCancionService implements IPlaylistCancionService{
	
	@Autowired
	PlaylistCancionRepository pcRepository;
	
	public void anadirCancion(Playlist playlist, Cancion cancion) {
		CancionPlaylist cp = new CancionPlaylist(playlist, cancion);
		pcRepository.save(cp);
	}
}
