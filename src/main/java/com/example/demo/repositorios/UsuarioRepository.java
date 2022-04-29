package com.example.demo.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.beans.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario,Integer> {

}