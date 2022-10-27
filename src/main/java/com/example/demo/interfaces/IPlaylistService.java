package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.beans.Playlist;

public interface IPlaylistService {
	public void crearPlaylist(Playlist playlist);
	public Playlist buscarPlaylist(String nombre);
	public List<Playlist> todasLasPlaylists(long id);
	public void eliminarPlaylist(Playlist playlist);
}