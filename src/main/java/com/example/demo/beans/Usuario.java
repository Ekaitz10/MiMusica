package com.example.demo.beans;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.Nullable;

@Entity
@Table(name="usuarios")
@RestResource(rel="usuarios", path="usuarios")
public class Usuario extends Persona {
	
	@NotNull
	@Column(nullable=false)
	private String username;
	@Column(nullable=false)
	private String password;
	
	@Nullable
	private String role;
	
	@OneToMany(mappedBy = "Usuario")
	private List<Playlist> playlists;
	
	public Usuario() {}
	
	public Usuario(@NotNull String nombre, @NotNull String apellido, @NotNull String nacionalidad,
			@NotNull int edad, @NotNull String username, @NotNull String password, @NotNull String role) {
		super(nombre, apellido, nacionalidad, edad);
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public List<Playlist> obtenerPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(password, playlists, role, username);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(password, other.password) && Objects.equals(playlists, other.playlists)
				&& Objects.equals(role, other.role) && Objects.equals(username, other.username);
	}
	
}