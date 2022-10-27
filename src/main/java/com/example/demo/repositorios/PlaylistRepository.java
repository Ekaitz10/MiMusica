package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.beans.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
	
	@Query(value = "SELECT * FROM playlists WHERE usuario_id = ?1", nativeQuery = true)
	List<Playlist> findAllByUsuario(long l);
	
	@Query(value = "SELECT * FROM playlists WHERE nombre = ?1", nativeQuery = true)
	List<Playlist> findByNombre(String nombre);

}