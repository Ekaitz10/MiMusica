package com.example.demo.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Cancion;
import com.example.demo.repositorios.CancionRepository;

@RestController
@RequestMapping(path="/design", produces="application/json")
@CrossOrigin(origins="*")
public class DesignCancionController {

	private CancionRepository cRepository;
	
	public DesignCancionController (CancionRepository cRepository) {
		this.cRepository = cRepository;
	}
	
	@GetMapping("/allsongs/")
	public Iterable<Cancion> todasLasCanciones(){
		PageRequest page = PageRequest.of(0, 12);
		return cRepository.findAll(page);
	}
}