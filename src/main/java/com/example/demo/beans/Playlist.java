package com.example.demo.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
@Entity
@Table(name="playlists")
public class Playlist {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column(nullable=false)
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="usuario_id", nullable=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Usuario Usuario;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name="cancion_playlist",
			joinColumns = {@JoinColumn(name="playlist_id")},
			inverseJoinColumns = {@JoinColumn(name="cancion_id")})
	private List<Cancion> canciones;
}

