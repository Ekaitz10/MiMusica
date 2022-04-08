package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Usuario;
import com.example.demo.db.GestorDB;
import com.example.demo.interfaces.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService {

	
	GestorDB gestor;
	
	@Autowired
	public UsuarioService(GestorDB gestor) {
		this.gestor = gestor;
	}
	
	@Override
	public void crearUsuario(Usuario usuario) {
		//si el usuario no existe, crear el usuario
		if(gestor.existeUsuario(usuario.getNombre()) == false) {
			gestor.crearUsuario(usuario);
		}
	}
	@Override
	public Usuario buscarUsuario(String nombre) {
		return gestor.buscarUsuario(nombre);
	}
	@Override
	public List<Usuario> todosLosUsuarios() {
		return gestor.todosLosUsuarios();
	}
	
	@Override
	public void eliminarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

}
