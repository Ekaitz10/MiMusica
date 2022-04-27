package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Playlist;
import com.example.demo.db.GestorDB;
import com.example.demo.db.JdbcPlaylistRepository;
import com.example.demo.db.JdbcUsuarioRepository;
import com.example.demo.interfaces.IPlaylistService;

@Service
public class PlaylistService implements IPlaylistService{
	
	JdbcPlaylistRepository jdbcPlaylistRepository;
	
	@Autowired
	public void setJdbcPlaylistRepository(JdbcPlaylistRepository jdbcPlaylistRepository) {
		this.jdbcPlaylistRepository = jdbcPlaylistRepository;
	}
	
	@Override
	public void crearPlaylist(Playlist playlist) {
		//si la playlist no existe, crear el usuario
		if(jdbcPlaylistRepository.existePlaylist(playlist.getNombre()) == false) {
			jdbcPlaylistRepository.crearPlaylist(playlist);
		}	
	}
	
	public Playlist buscarPlaylist(String nombre) {
		return jdbcPlaylistRepository.buscarPlaylist(nombre);
	}
	
	@Override
	public List<Playlist> todasLasPlaylists(int id) {
		return jdbcPlaylistRepository.todasLasPlaylists(id);
	}

	@Override
	public void anadirCancion(int playlistid, int cancionid) {
		jdbcPlaylistRepository.anadirCancion(playlistid, cancionid);
		
	}
	public int buscarIdPlaylist(String nombre) {
		return jdbcPlaylistRepository.buscarIdPlaylist(nombre);
	}
	@Override
	public void eliminarPlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		
	}

}
