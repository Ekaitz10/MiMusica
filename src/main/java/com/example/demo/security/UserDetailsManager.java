package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.beans.SecurityUser;
import com.example.demo.beans.Usuario;
import com.example.demo.repositorios.UsuarioRepository;

@Service
public class UserDetailsManager implements UserDetailsService{

	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario u = userRepository.findUserByUsername(username).get(0);
		return new SecurityUser(u);
	}
	
	public void addUser(String nombre, String apellido, String nacionalidad, int edad, String username, String password, String role) {
		userRepository.save(new Usuario(nombre, apellido, nacionalidad, edad, username, password, role));
	}
	
	public void delete(long id) {
		userRepository.deleteById(id);
	}	
}
