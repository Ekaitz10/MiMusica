package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.beans.Usuario;

public interface IUsuarioService {
	public void crearUsuario(Usuario usuario);
	public List<Usuario> todosLosUsuarios();
	public void eliminarUsuario(Usuario usuario);
}
