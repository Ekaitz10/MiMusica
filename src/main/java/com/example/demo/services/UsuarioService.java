package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Usuario;
import com.example.demo.db.JdbcUsuarioRepository;
import com.example.demo.interfaces.IUsuarioService;
import com.example.demo.repositorios.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

	JdbcUsuarioRepository jdbcUsuarioRepository;
	
	@Autowired
	UsuarioRepository uServ;
	
	@Autowired
	public void setJdbcUsuarioRepository(JdbcUsuarioRepository jdbcUsuarioRepository) {
		this.jdbcUsuarioRepository = jdbcUsuarioRepository;
	}
	
	@Override
	public void crearUsuario(Usuario usuario) {
		//si el usuario no existe, crear el usuario
		if(uServ.existsById(usuario.getId()) == false) {
			uServ.save(usuario);
		}
	}
	public Usuario buscarUsuario(String nombre) {
		return jdbcUsuarioRepository.buscarUsuario(nombre);
	}
	@Override
	public List<Usuario> todosLosUsuarios() {
		return jdbcUsuarioRepository.todosLosUsuarios();
	}
	public int buscarIdUsuario(String nombre) {
		return jdbcUsuarioRepository.buscarIdUsuario(nombre);
	}
	@Override
	public void eliminarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

}