package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.beans.Artista;

public interface IArtistaService {
	public void crearArtista(Artista artista);
	public List<Artista> todosLosArtistas();
	public Artista buscarArtista(Long idArtista);
	public void eliminarArtista(Artista artista);
}
