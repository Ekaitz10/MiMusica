package com.example.demo.repositorios;

import java.util.List;

import com.example.demo.beans.Artista;

public interface ArtistaRepository extends PersonaRepository<Artista, Long>{
	List<Artista> findByNombreArtistico(String nombreArtistico);
}