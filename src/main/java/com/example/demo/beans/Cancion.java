package com.example.demo.beans;

public class Cancion {
	private String titulo;
	private int artista_id;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getArtista_id() {
		return artista_id;
	}
	public void setArtista_id(int artista_id) {
		this.artista_id = artista_id;
	}
	@Override
	public String toString() {
		return "Cancion [titulo=" + titulo + ", artista_id=" + artista_id + "]";
	}
	
}
