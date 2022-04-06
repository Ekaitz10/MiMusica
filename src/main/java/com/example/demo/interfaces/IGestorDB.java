package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.beans.Artista;
import com.example.demo.beans.Cancion;
import com.example.demo.beans.Playlist;
import com.example.demo.beans.Usuario;

public interface IGestorDB {
	
	//usuarios
	public void crearUsuario(Usuario usuario);
	public List<Usuario> todosLosUsuarios();
	public void eliminarUsuario(Usuario usuario);
	//playlists
	public void crearPlaylist(Playlist playlist);
	public List<Playlist> todasLasPlaylists();
	public void añadirCancion(Cancion cancion);
	public void eliminarPlaylist(Playlist playlist);
	//artistas
	public void crearArtista(Artista artista);
	public List<Artista> todosLosArtistas();
	public void eliminarArtista(Artista artista);
	//canciones
	public void crearCancion(Cancion cancion);
	public List<Cancion> todasLasCanciones();
	public List<Cancion> buscarCancion();
	public void eliminarCancion(Cancion cancion);
	
}