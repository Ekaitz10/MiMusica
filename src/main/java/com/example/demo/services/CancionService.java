package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.beans.Cancion;
import com.example.demo.interfaces.ICancionService;

@Service
public class CancionService implements ICancionService{

	@Override
	public void crearCancion(Cancion cancion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cancion> todasLasCanciones() {
		// TODO Auto-generated method stub
		return null;
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
