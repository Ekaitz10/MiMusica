package com.example.demo.beans;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="usuarios")
public class Usuario extends Persona {
	
	@NotNull
	@Column(nullable=false)
	private String nombreUsuario;
		
	@OneToMany(mappedBy = "Usuario")
	private List<Playlist> playlists;
	
	public Usuario() {}
	
	public Usuario(@NotNull String nombre, @NotNull String apellido, @NotNull String nacionalidad,
			@NotNull int edad, @NotNull String nombreUsuario) {
		super(nombre, apellido, nacionalidad, edad);
		this.nombreUsuario = nombreUsuario;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nombreUsuario, other.nombreUsuario);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombreUsuario);
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public List<Playlist> obtenerPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
	
}