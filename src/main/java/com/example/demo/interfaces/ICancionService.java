package com.example.demo.interfaces;

import java.util.List;
import java.util.Optional;

import com.example.demo.beans.Cancion;

public interface ICancionService {
	public void crearCancion(Cancion cancion);
	public List<Cancion> todasLasCanciones();
	public List<Cancion> todasLasCancionesDePlaylist(Long playlist_id);
	public Cancion buscarCancionPorId(Long id);
	public Cancion buscarCancionPorTituloYArtista(String titulo, Long artistaId);
	public void eliminarCancion(Cancion cancion);
}
