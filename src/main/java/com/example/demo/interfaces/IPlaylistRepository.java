package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.beans.Playlist;

public interface IPlaylistRepository {
	public void crearPlaylist(Playlist playlist);
	public Playlist buscarPlaylist(String nombre);
	public List<Playlist> todasLasPlaylists(int id);
	boolean existePlaylist(String nombre);
	public void anadirCancion(Playlist playlist, int playlistid, int cancionid);
	public void eliminarPlaylist(Playlist playlist);
	public int buscarIdPlaylist(String nombre);
}
