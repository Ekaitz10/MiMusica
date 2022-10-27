package com.example.demo.beans;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nombre;
	@NotNull
	@Column(nullable=false)
	private String apellido;
	@NotNull
	@Column(nullable=false)
	private String nacionalidad;
	@NotNull
	@Column(nullable=false)
	private int edad;
	
	public Persona() {
	}
	
	public Persona(@NotNull String nombre, @NotNull String apellido, @NotNull String nacionalidad,
			@NotNull int edad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.edad = edad;
	}
	
}