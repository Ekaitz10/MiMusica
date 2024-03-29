package com.example.demo.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.springframework.lang.Nullable;

import lombok.Data;

@Data
@Entity
@Table(name="cancion_playlist")
@Immutable
public class CancionPlaylist {

	@Embeddable
	public static class Id implements Serializable {
		
		private static final long serialVersionUID = 1L;

		@Column(name = "PLAYLIST_ID")
		private Long playlistId;
		
		@Column(name = "CANCION_ID")
		private Long cancionId;
		
		public Id() {
		}
		
		public Id(Long playlistId, Long cancionId) {
			this.playlistId = playlistId;
			this.cancionId = cancionId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(cancionId, playlistId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Id other = (Id) obj;
			return Objects.equals(cancionId, other.cancionId) && Objects.equals(playlistId, other.playlistId);
		}

		@Override
		public String toString() {
			return "Id [playlistId=" + playlistId + ", cancionId=" + cancionId + "]";
		}
	}
		
	@EmbeddedId
	private Id id = new Id();
	
	@Column(updatable = false)
	@Nullable
	@CreationTimestamp
	private LocalDateTime addedOn;
	
	@ManyToOne
	@JoinColumn(
			name = "PLAYLIST_ID",
			insertable = false, updatable = false)
	private Playlist playlist;
	
	@ManyToOne
	@JoinColumn(
			name = "CANCION_ID",
			insertable = false, updatable = false)
	private Cancion cancion;
	
	public CancionPlaylist() {
	}
	
	public CancionPlaylist(Playlist playlist, Cancion cancion) {
		this.playlist = playlist;
		this.cancion = cancion;
		this.id.playlistId = playlist.getId();
		this.id.cancionId = cancion.getId();
	}
}