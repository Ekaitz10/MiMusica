package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.beans.Cancion;

public interface ICancionService {
	public void crearCancion(Cancion cancion);
	public List<Cancion> todasLasCanciones();
	public List<Cancion> todasLasCanciones(int playlist_id);
	public Cancion buscarCancion(int id);
	public void eliminarCancion(Cancion cancion);
	public int buscarIdCancion(String nombre);
}
