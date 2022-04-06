package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.example.demo.beans.Usuario;
import com.example.demo.db.GestorDB;
import com.example.demo.interfaces.IUsuarioService;

public class UsuarioService implements IUsuarioService {

	
	GestorDB gestor = new GestorDB("${spring.datasource.url}","${spring.datasource.driver-class-name}","${spring.datasource.username}","${spring.datasource.password}");
	
	@Override
	public void crearUsuario(Usuario usuario) {
		//si el usuario no existe, crear el usuario
		if(gestor.buscarUsuario(usuario.getNombre()) == false) {
			gestor.crearUsuario(usuario);
		}
	}

	@Override
	public List<Usuario> todosLosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

}
