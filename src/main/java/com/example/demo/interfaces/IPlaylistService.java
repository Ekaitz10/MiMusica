package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.beans.Cancion;
import com.example.demo.beans.Playlist;

public interface IPlaylistService {
	public void crearPlaylist(Playlist playlist);
	public List<Playlist> todasLasPlaylists();
	public void añadirCancion(Cancion cancion);
	public void eliminarPlaylist(Playlist playlist);
}