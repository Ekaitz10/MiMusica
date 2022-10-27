package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Cancion;
import com.example.demo.interfaces.ICancionService;
import com.example.demo.repositorios.CancionRepository;

@Service
public class CancionService implements ICancionService{
	
	@Autowired
	CancionRepository cRepository;
	
	@Override
	public void crearCancion(Cancion cancion) {
		int cont = 0;
		List<Cancion> cancionesEncontradas = cRepository.findAllByTitulo(cancion.getTitulo());			
		if(cancionesEncontradas.size() == 0) {
			cRepository.save(cancion);
		}else {
			for(Cancion cancionEncontrada : cancionesEncontradas) {
				if(cancionEncontrada.getTitulo().equals(cancion.getTitulo()) && cancionEncontrada.getArtista().getNombreArtistico().equals(cancion.getArtista().getNombreArtistico())) {
					cont++;
				}
			}
			if(cont == 0) {
				cRepository.save(cancion);
			}
		}
	}

	@Override
	public List<Cancion> todasLasCanciones() {
		return cRepository.findAll();
	}
	
	@Override
	public List<Cancion> todasLasCancionesDePlaylist(Long playlist_id) {
		return cRepository.findAllByPlaylistId(playlist_id);
	}
	
	@Override
	public Optional<Cancion> buscarCancionPorId(Long id) {
		return cRepository.findById(id);
	}
	
	@Override
	public Cancion buscarCancionPorTituloYArtista(String titulo, Long artistaId) {
		return cRepository.findByTituloAndArtista(titulo, artistaId);
	}
	
	@Override
	public void eliminarCancion(Cancion cancion) {
		// TODO Auto-generated method stub
		
	}

	

}
