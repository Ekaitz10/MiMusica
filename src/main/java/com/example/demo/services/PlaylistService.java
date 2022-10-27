package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Playlist;
import com.example.demo.interfaces.IPlaylistService;
import com.example.demo.repositorios.PlaylistRepository;

@Service
public class PlaylistService implements IPlaylistService{
	
	@Autowired
	PlaylistRepository pRepository;
	
	@Override
	public void crearPlaylist(Playlist playlist) {
		List<Playlist> playlistEncontrados = pRepository.findByNombre(playlist.getNombre());
		if(playlistEncontrados.size() == 0) {
			pRepository.save(playlist);
		}
	}
	
	public Playlist buscarPlaylist(String nombre) {
		return pRepository.findByNombre(nombre).get(0);
	}
	
	@Override
	public List<Playlist> todasLasPlaylists(long l) {
		List<Playlist> playlists = pRepository.findAllByUsuario(l);
		return playlists;
	}
	
	public Long buscarIdPlaylist(String nombre) {
		return pRepository.findByNombre(nombre).get(0).getId();
	}
	@Override
	public void eliminarPlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		
	}

}
