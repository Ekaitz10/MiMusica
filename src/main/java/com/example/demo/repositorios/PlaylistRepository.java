package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.beans.Playlist;
import com.example.demo.beans.Usuario;

public interface PlaylistRepository extends CrudRepository<Playlist, Integer> {
	
	@Query(value = "SELECT * FROM playlists WHERE usuario_id = ?1", nativeQuery = true)
	List<Playlist> findAllByUsuario(int usuario_id);
	
	@Query(value = "SELECT * FROM playlists WHERE nombre = ?1", nativeQuery = true)
	List<Playlist> findByName(String nombre);
}