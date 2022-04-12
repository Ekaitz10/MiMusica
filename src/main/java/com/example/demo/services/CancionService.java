package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Cancion;
import com.example.demo.db.GestorDB;
import com.example.demo.interfaces.ICancionService;

@Service
public class CancionService implements ICancionService{
	GestorDB gestor;
	
	@Autowired
	public CancionService(GestorDB gestor){
		this.gestor = gestor;
	}
	
	@Override
	public void crearCancion(Cancion cancion) {
		if(gestor.existeCancion(cancion.getTitulo()) == false) {
			gestor.crearCancion(cancion);
		}
		
	}

	@Override
	public List<Cancion> todasLasCanciones() {
		return gestor.todasLasCanciones();
	}
	public int buscarIdCancion(String nombre) {
		return gestor.buscarIdCancion(nombre);
	}
	@Override
	public List<Cancion> todasLasCanciones(int id) {
		return gestor.todasLasCanciones(id);
	}
	@Override
	public List<Cancion> buscarCancion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarCancion(Cancion cancion) {
		// TODO Auto-generated method stub
		
	}

}
