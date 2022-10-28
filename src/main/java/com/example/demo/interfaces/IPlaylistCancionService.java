package com.example.demo.interfaces;

import com.example.demo.beans.Cancion;
import com.example.demo.beans.CancionPlaylist;
import com.example.demo.beans.Playlist;

public interface IPlaylistCancionService {
	public void anadirCancion(Playlist playlist, Cancion cancion);
	public CancionPlaylist buscarPorIds(Playlist playlist, Cancion cancion);
}