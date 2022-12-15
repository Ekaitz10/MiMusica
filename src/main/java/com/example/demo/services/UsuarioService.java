package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Usuario;
import com.example.demo.interfaces.IUsuarioService;
import com.example.demo.repositorios.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {
	
	@Autowired
	UsuarioRepository uRepository;
	
	@Override
	public void crearUsuario(Usuario usuario) {
		//si el usuario no existe, crear el usuario
		List<Usuario> usuariosEncontrados = uRepository.findUserByUsername(usuario.getUsername());
		if(usuariosEncontrados.size() == 0) {
			uRepository.save(usuario);
		}
	}
	public Usuario buscarUsuario(String nombre) {
		return uRepository.findUserByUsername(nombre).get(0);
	}
	@Override
	public List<Usuario> todosLosUsuarios() {
		return uRepository.findAll();
	}
	public long buscarIdUsuario(String nombre) {
		return uRepository.findUserByUsername(nombre).get(0).getId();
		
	}
	@Override
	public void eliminarUsuario(Usuario usuario) {
		uRepository.delete(usuario);
	}

}