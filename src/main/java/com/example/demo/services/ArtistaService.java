package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Artista;
import com.example.demo.db.GestorDB;
import com.example.demo.interfaces.IArtistaService;

@Service
public class ArtistaService implements IArtistaService{
	
	GestorDB gestor;
	
	@Autowired
	public ArtistaService(GestorDB gestor) {
		this.gestor = gestor;
	}
	@Override
	public void crearArtista(Artista artista) {
		if(gestor.existeArtista(artista.getNombre()) == false) {
			gestor.crearArtista(artista);
		}
	}
	public int buscarIdArtista(String nombre) {
		return gestor.buscarIdArtista(nombre);
	}
	@Override
	public List<Artista> todosLosArtistas() {
		return gestor.todosLosArtistas();
	}

	@Override
	public void eliminarArtista(Artista artista) {
		// TODO Auto-generated method stub
		
	}

}
