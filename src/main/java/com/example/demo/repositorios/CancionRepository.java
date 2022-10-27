package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.beans.Cancion;

public interface CancionRepository extends JpaRepository<Cancion, Long> {
	
	@Query(value = "SELECT * FROM canciones WHERE titulo = ?1", nativeQuery = true)
	List<Cancion> findAllByTitulo(String titulo);
	
	@Query(value = "SELECT * FROM canciones WHERE titulo = ?1 AND artista_id = ?2", nativeQuery = true)
	Cancion findByTituloAndArtista(String titulo, Long ArtistaId);
	
	@Query(value = "SELECT * FROM cancion_playlist WHERE playlist_id = ?1", nativeQuery = true)
	List<Cancion> findAllByPlaylistId(Long playlist_id);
}