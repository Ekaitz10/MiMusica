package com.example.demo.repositorios;

import java.util.List;

import com.example.demo.beans.Usuario;

public interface UsuarioRepository extends PersonaRepository<Usuario,Long> {
	List<Usuario> findUserByUsername(String nombre);
}