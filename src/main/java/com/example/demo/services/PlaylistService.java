package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Playlist;
import com.example.demo.db.JdbcPlaylistRepository;
import com.example.demo.interfaces.IPlaylistService;
import com.example.demo.repositorios.PlaylistRepository;

@Service
public class PlaylistService implements IPlaylistService{
	
	JdbcPlaylistRepository jdbcPlaylistRepository;
	
	@Autowired
	PlaylistRepository pServ;
	
	@Autowired
	public void setJdbcPlaylistRepository(JdbcPlaylistRepository jdbcPlaylistRepository) {
		this.jdbcPlaylistRepository = jdbcPlaylistRepository;
	}
	
	@Override
	public void crearPlaylist(Playlist playlist) {
		//si la playlist no existe, crear el usuario
		if(pServ.existsById(playlist.getId()) == false) {
			pServ.save(playlist);
		}
	}
	
	public Playlist buscarPlaylist(String nombre) {
		return pServ.findByName(nombre).get(0);
	}
	
	@Override
	public List<Playlist> todasLasPlaylists(int id) {
		return pServ.findAllByUsuario(id);
	}

	@Override
	public void anadirCancion(Playlist playlist,int playlistid, int cancionid) {
		jdbcPlaylistRepository.anadirCancion(playlist, playlistid, cancionid);
	}
	public int buscarIdPlaylist(String nombre) {
		return jdbcPlaylistRepository.buscarIdPlaylist(nombre);
	}
	@Override
	public void eliminarPlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		
	}

}
