package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.beans.Artista;
import com.example.demo.beans.Usuario;
import com.example.demo.repositorios.ArtistaRepository;
import com.example.demo.repositorios.UsuarioRepository;

@SpringBootTest
class PersonaArtistaUsuarioTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Test
	void test() {
		Usuario user = new Usuario("Ekaitz","Soto","España",21, "Ekaitzsoto10");
		usuarioRepository.save(user);
		
		Artista artista = new Artista("Jaques", "Webster", "Estados Unidos", 30, "Travis Scott", "Hip Hop", "Voz","Cactus Jack Records");
		artistaRepository.save(artista);
		
		List<Usuario> usuarios = usuarioRepository.findByNombre("Ekaitz");
		List<Artista> artistas = artistaRepository.findByNombre("Jaques");
		
		assertAll(
				() -> assertEquals("Ekaitz", usuarios.get(0).getNombre()),
				() -> assertEquals("Travis Scott", artistas.get(0).getNombreArtistico())
		);
		
	}
}
