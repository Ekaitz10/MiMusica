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
	public Playlist buscarPlaylist(String nombre);
	public List<Playlist> todasLasPlaylists(int id);
	boolean existePlaylist(String nombre);
	public void anadirCancion(int playlistid, int cancionid);
	public void eliminarPlaylist(Playlist playlist);
	public int buscarIdPlaylist(String nombre);
	//artistas
	public void crearArtista(Artista artista);
	boolean existeArtista(String nombre);
	public List<Artista> todosLosArtistas();
	public void eliminarArtista(Artista artista);
	public int buscarIdArtista(String nombre);
	//canciones
	public void crearCancion(Cancion cancion);
	boolean existeCancion(String titulo);
	public List<Cancion> todasLasCanciones(int playlist_id);
	public Cancion buscarCancion(String titulo);
	public void eliminarCancion(Cancion cancion);
	public int buscarIdCancion(String nombre);
}