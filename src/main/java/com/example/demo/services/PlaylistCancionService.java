package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Cancion;
import com.example.demo.beans.CancionPlaylist;
import com.example.demo.beans.CancionPlaylist.Id;
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
	
	public CancionPlaylist buscarPorIds(Long playlist, Long cancion) {
		List <CancionPlaylist> todos = pcRepository.findAll();
		for(CancionPlaylist actual : todos) {
			System.err.println(actual.getId().toString());
			Id id = new Id(playlist, cancion);
			if(actual.getId().equals(id)) {
				return actual;
			}
		}
		return null;
	}
	
	public void eliminarCancion(CancionPlaylist cp) {
		pcRepository.delete(cp);
	}
}
