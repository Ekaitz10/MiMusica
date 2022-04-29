package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Cancion;
import com.example.demo.db.JdbcCancionRepository;
import com.example.demo.interfaces.ICancionService;
import com.example.demo.repositorios.CancionRepository;

@Service
public class CancionService implements ICancionService{
	
	JdbcCancionRepository jdbcCancionRepository;
	@Autowired
	CancionRepository cServ;
	@Autowired
	public void setJdbcPlaylistRepository(JdbcCancionRepository jdbcCancionRepository) {
		this.jdbcCancionRepository = jdbcCancionRepository;
	}
	
	@Override
	public void crearCancion(Cancion cancion) {
		if(cServ.existsById(cancion.getId()) == false) {
			cServ.save(cancion);
		}
		
	}

	@Override
	public List<Cancion> todasLasCanciones() {
		// TODO Auto-generated method stub
		return jdbcCancionRepository.todasLasCanciones();
	}
	
	@Override
	public List<Cancion> todasLasCanciones(int playlist_id) {
		return jdbcCancionRepository.todasLasCanciones(playlist_id);
	}
	public int buscarIdCancion(String nombre) {
		return jdbcCancionRepository.buscarIdCancion(nombre);
	}

	@Override
	public Cancion buscarCancion(int id) {
		// TODO Auto-generated method stub
		return jdbcCancionRepository.buscarCancion(id);
	}

	@Override
	public void eliminarCancion(Cancion cancion) {
		// TODO Auto-generated method stub
		
	}

}
