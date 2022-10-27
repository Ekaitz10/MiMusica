package com.example.demo.beans;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
@Entity
@Table(name="artistas")
public class Artista extends Persona{
	
	@NotNull
	@Column(nullable=false)
	private String nombreArtistico;
	
	@NotNull
	@Column(nullable=false)
	private String genero;
	
	@NotNull
	@Column(nullable=false)
	private String descripcion;
	
	@NotNull
	@Column(nullable=false)
	private String sello;

	public Artista(@NotNull String nombre, @NotNull String apellido, @NotNull String nacionalidad,
			@NotNull int edad, @NotNull String nombreArtistico, @NotNull String genero, @NotNull String descripcion, @NotNull String sello) {
		super(nombre, apellido, nacionalidad, edad);
		this.nombreArtistico = nombreArtistico;
		this.genero = genero;
		this.descripcion = descripcion;
		this.sello = sello;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
		return Objects.equals(genero, other.genero) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(sello, other.sello);
	}

	@Override
	public int hashCode() {
		return Objects.hash(genero, descripcion, sello);
	}
	
	
}
