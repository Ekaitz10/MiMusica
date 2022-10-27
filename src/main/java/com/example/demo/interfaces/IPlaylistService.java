package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.beans.Playlist;
import com.example.demo.beans.Usuario;

public interface IPlaylistService {
	public void crearPlaylist(Playlist playlist, Usuario usuario);
	public Playlist buscarPlaylist(String nombre, Usuario usuario);
	public List<Playlist> todasLasPlaylists(long id);
	public void eliminarPlaylist(Playlist playlist);
}