package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Cancion;
import com.example.demo.db.GestorDB;
import com.example.demo.db.JdbcCancionRepository;
import com.example.demo.interfaces.ICancionService;

@Service
public class CancionService implements ICancionService{
	
	JdbcCancionRepository jdbcCancionRepository;
	
	@Autowired
	public void setJdbcPlaylistRepository(JdbcCancionRepository jdbcCancionRepository) {
		this.jdbcCancionRepository = jdbcCancionRepository;
	}
	
	@Override
	public void crearCancion(Cancion cancion) {
		if(jdbcCancionRepository.existeCancion(cancion.getTitulo()) == false) {
			jdbcCancionRepository.crearCancion(cancion);
		}
		
	}

	@Override
	public List<Cancion> todasLasCanciones(int playlist_id) {
		return jdbcCancionRepository.todasLasCanciones(playlist_id);
	}
	public int buscarIdCancion(String nombre) {
		return jdbcCancionRepository.buscarIdCancion(nombre);
	}

	@Override
	public Cancion buscarCancion(String titulo) {
		// TODO Auto-generated method stub
		return jdbcCancionRepository.buscarCancion(titulo);
	}

	@Override
	public void eliminarCancion(Cancion cancion) {
		// TODO Auto-generated method stub
		
	}
	
}
