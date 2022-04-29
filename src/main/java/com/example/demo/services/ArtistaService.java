package com.example.demo.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Artista;
import com.example.demo.db.JdbcArtistaRepository;
import com.example.demo.interfaces.IArtistaService;
import com.example.demo.repositorios.ArtistaRepository;

@Service
public class ArtistaService implements IArtistaService{
	
	JdbcArtistaRepository jdbcArtistaRepository;
	
	@Autowired
	ArtistaRepository aServ;
	@Autowired
	public void setJdbcArtistaRepository(JdbcArtistaRepository jdbcArtistaRepository) {
		this.jdbcArtistaRepository = jdbcArtistaRepository;
	}
	
	@Override
	public void crearArtista(Artista artista) {
		if(aServ.existsById(artista.getId()) == false) {
			aServ.save(artista);
		}
	}
	public Artista buscarArtista() {
		Iterable<Artista> art = aServ.findAll();
		Iterator<Artista> it = art.iterator();
		Artista artista = it.next();
		return artista;
	}
	public int buscarIdArtista(String nombre) {
		return jdbcArtistaRepository.buscarIdArtista(nombre);
	}
	@Override
	public List<Artista> todosLosArtistas() {
		return jdbcArtistaRepository.todosLosArtistas();
	}

	@Override
	public void eliminarArtista(Artista artista) {
		// TODO Auto-generated method stub
		
	}

}
