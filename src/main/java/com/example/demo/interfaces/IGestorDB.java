package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.beans.Cancion;

public interface IGestorDB {
	//consultas
	public List<Cancion> buscarPlaylist();
	public Cancion buscarCancion();
	//actualizar
	
	public void crearPlaylist();
	public void eliminarPlaylist();
	
	public void añadirCancion(List<Cancion> playlist);
}
