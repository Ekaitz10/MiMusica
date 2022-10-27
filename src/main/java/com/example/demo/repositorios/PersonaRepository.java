package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.demo.beans.Persona;

@NoRepositoryBean
public interface PersonaRepository <T extends Persona, ID> extends JpaRepository<T, ID>{
	List<T> findByNombre(String nombre);
}