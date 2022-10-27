package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Artista;
import com.example.demo.interfaces.IArtistaService;
import com.example.demo.repositorios.ArtistaRepository;

@Service
public class ArtistaService implements IArtistaService{
	
	@Autowired
	ArtistaRepository aRepository;
	
	@Override
	public void crearArtista(Artista artista) {
		List<Artista> usuariosEncontrados = aRepository.findByNombreArtistico(artista.getNombreArtistico());
		if(usuariosEncontrados.size() == 0) {
			aRepository.save(artista);
		}
	}
	public Artista buscarArtista(Long id) {
		return aRepository.getById(id);
	}
	public long buscarIdArtista(String nombreArtistico) {
		return aRepository.findByNombreArtistico(nombreArtistico).get(0).getId();
	}
	@Override
	public List<Artista> todosLosArtistas() {
		return aRepository.findAll();
	}

	@Override
	public void eliminarArtista(Artista artista) {
		// TODO Auto-generated method stub
		
	}

}
