package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.beans.Artista;

public interface IArtistaRepository {
	public void crearArtista(Artista artista);
	boolean existeArtista(String nombre);
	public List<Artista> todosLosArtistas();
	public void eliminarArtista(Artista artista);
	public int buscarIdArtista(String nombre);
}
