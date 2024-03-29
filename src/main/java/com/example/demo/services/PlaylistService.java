package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Playlist;
import com.example.demo.beans.Usuario;
import com.example.demo.interfaces.IPlaylistService;
import com.example.demo.repositorios.PlaylistRepository;

@Service
public class PlaylistService implements IPlaylistService{
	
	@Autowired
	PlaylistRepository pRepository;
	
	@Override
	public void crearPlaylist(Playlist playlist, Usuario usuario) {
		List<Playlist> playlistEncontrados = pRepository.findByNombre(playlist.getNombre(), usuario.getId());
		if(playlistEncontrados.size() == 0) {
			pRepository.save(playlist);
		}
	}
	
	public Playlist buscarPlaylist(String nombre, Usuario usuario) {
		return pRepository.findByNombre(nombre, usuario.getId()).get(0);
	}
	
	public Playlist buscarPlaylistPorId(Long id) {
		return pRepository.getById(id);
	}
	@Override
	public List<Playlist> todasLasPlaylists(long l) {
		List<Playlist> playlists = pRepository.findAllByUsuario(l);
		return playlists;
	}
	
	public Long buscarIdPlaylist(String nombre, Usuario usuario) {
		Long id = pRepository.findByNombre(nombre, usuario.getId()).get(0).getId();
		return id;
	}
	@Override
	public void eliminarPlaylist(Playlist playlist) {
		
	}

}
