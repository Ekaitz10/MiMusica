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
	public Usuario buscarUsuario(String nombre);
	boolean existeUsuario(String nombre);
	public void eliminarUsuario(Usuario usuario);
	public int buscarIdUsuario(String nombre);
	//playlists
	public void crearPlaylist(Playlist playlist);
	public List<Playlist> todasLasPlaylists();
	boolean existePlaylist(String nombre);
	public void anadirCancion(Cancion cancion);
	public void eliminarPlaylist(Playlist playlist);
	public int buscarIdPlaylist(String nombre);
	//artistas
	public void crearArtista(Artista artista);
	public List<Artista> todosLosArtistas();
	public void eliminarArtista(Artista artista);
	public int buscarIdArtista(String nombre);
	//canciones
	public void crearCancion(Cancion cancion);
	public List<Cancion> todasLasCanciones();
	public List<Cancion> buscarCancion(String nombre);
	public void eliminarCancion(Cancion cancion);
	public int buscarIdCancion(String nombre);
}