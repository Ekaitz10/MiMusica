package com.example.demo.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.beans.Cancion;

public interface CancionRepository extends CrudRepository<Cancion, Integer> {

}
