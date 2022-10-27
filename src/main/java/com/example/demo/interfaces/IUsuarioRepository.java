package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.beans.Usuario;

public interface IUsuarioRepository {
	public void crearUsuario(Usuario usuario);
	public List<Usuario> todosLosUsuarios();
	public Usuario buscarUsuario(String nombre);
	boolean existeUsuario(String nombre);
	public void eliminarUsuario(Usuario usuario);
	public int buscarIdUsuario(String nombre);
}
