package com.example.demo.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.beans.Cancion;
import com.example.demo.beans.Playlist;
import com.example.demo.interfaces.IPlaylistRepository;

@Repository
public class JdbcPlaylistRepository implements IPlaylistRepository {
	private JdbcTemplate jdbc;
	private JdbcCancionRepository jdbcCancionRepository;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	public JdbcPlaylistRepository(JdbcTemplate jdbc, JdbcCancionRepository jdbcCancionRepository, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbc = jdbc;
		this.jdbcCancionRepository = jdbcCancionRepository;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	private Playlist mapRowToPlaylist(ResultSet rs, int rowNum) throws SQLException {
		/*Playlist playlist = new Playlist();
		playlist.setNombre(rs.getString("nombre"));
		playlist.setUsuario();
		return playlist;*/
		return null;
	}
	
	@Override
	public void crearPlaylist(Playlist playlist) {
		/*jdbc.update("INSERT INTO playlists (nombre, usuario_id) VALUES (?,?)",
				playlist.getNombre(),
				playlist.getIdUsuario());
	*/
	}

	@Override
	public Playlist buscarPlaylist(String nombre) {
		return jdbc.query("SELECT * FROM playlists WHERE nombre = ?", this::mapRowToPlaylist, nombre).get(0);
	}

	@Override
	public List<Playlist> todasLasPlaylists(int id) {
		return (List) jdbc.query("SELECT * FROM playlists WHERE usuario_id = ?", this::mapRowToPlaylist, id);
	}

	@Override
	public boolean existePlaylist(String nombre) {
		Iterable<Playlist> resultado = jdbc.query("SELECT * FROM playlists WHERE nombre = ?", this::mapRowToPlaylist, nombre);
		Iterator<Playlist> it = resultado.iterator();
		if(it.hasNext()) {
        	return true;
        }else {
        	return false;
        }
	}

	@Override
	public void anadirCancion(Playlist playlist, int playlistid, int cancionid) {
		Cancion cancion = jdbcCancionRepository.buscarCancion(cancionid);
		List<Cancion> canciones  = playlist.getCanciones();
		if(canciones == null) {
			 canciones = new ArrayList<Cancion>();
		}
		canciones.add(cancion);
		playlist.setCanciones(canciones);
		System.out.println(canciones.size());
		jdbc.update("insert into cancion_playlist (playlist_id, cancion_id) values (?,?)",playlistid, cancionid);
	}

	@Override
	public void eliminarPlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int buscarIdPlaylist(String nombre) {
		int id;
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("nombre", nombre);
		int rs = namedParameterJdbcTemplate.queryForObject("SELECT id FROM playlists WHERE nombre = :nombre",namedParameters, Integer.class);
		id = rs;
		return id;
	}
}
