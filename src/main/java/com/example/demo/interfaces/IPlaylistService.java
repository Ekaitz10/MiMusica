package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.beans.Cancion;
import com.example.demo.beans.Playlist;

public interface IPlaylistService {
	public void crearPlaylist(Playlist playlist);
	public Playlist buscarPlaylist(String nombre);
	public List<Playlist> todasLasPlaylists(int id);
	public void anadirCancion(int playlistid, int cancionid);
	public void eliminarPlaylist(Playlist playlist);
}