package com.example.demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.beans.Artista;
import com.example.demo.beans.Cancion;
import com.example.demo.interfaces.ICancionRepository;

@Repository
public class JdbcCancionRepository implements ICancionRepository{

	private JdbcTemplate jdbc;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public JdbcCancionRepository(JdbcTemplate jdbc,NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbc = jdbc;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	private Cancion mapRowToCancion(ResultSet rs, int rowNum) throws SQLException {
		Cancion cancion = new Cancion();
		cancion.setTitulo(rs.getString("titulo"));
		cancion.setArtista_id(rs.getInt("artista_id"));
		return cancion;
	}
	

	@Override
	public void crearCancion(Cancion cancion) {
		jdbc.update("insert into canciones (titulo, artista_id) values (?,?)",
				cancion.getTitulo(),
				cancion.getArtista_id());
	}
	
	@Override
	public boolean existeCancion(String titulo) {
		Iterable<Cancion> resultado = jdbc.query("SELECT * FROM canciones WHERE titulo = ?", this::mapRowToCancion, titulo);
		Iterator<Cancion> it = resultado.iterator();
		if(it.hasNext()) {
        	return true;
        }else {
        	return false;
        }
	}

	@Override
	public List<Cancion> todasLasCanciones(int playlist_id) {
		List<Cancion> canciones = new ArrayList<Cancion>();
		List<Integer> rs1 = (List) jdbc.queryForList("SELECT cancion_id FROM cancion_playlist WHERE playlist_id = ?", playlist_id);
		Iterator<Integer> it1 = rs1.iterator();
		while(it1.hasNext()) {
			Iterable<Cancion> rs2 = jdbc.query("SELECT * FROM canciones WHERE id = ?", this::mapRowToCancion, it1);
			Iterator<Cancion> it2 = rs2.iterator();
			if(it2.hasNext()) {
				Cancion cancion = new Cancion();
				cancion.setTitulo(it2.next().getTitulo());
				cancion.setArtista_id(it2.next().getArtista_id());
				canciones.add(cancion);
	        }
		}    
		return canciones;
	}

	@Override
	public Cancion buscarCancion(String titulo) {
		return jdbc.query("SELECT * FROM cancion WHERE titulo = ?", this::mapRowToCancion, titulo).get(0);
	}
	
	@Override
	public int buscarIdCancion(String nombre) {
		int id;
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("nombre", nombre);
		int rs = namedParameterJdbcTemplate.queryForObject("SELECT id FROM canciones WHERE nombre = :nombre",namedParameters, Integer.class);
		id = rs;
		return id;
	}
	
	@Override
	public void eliminarCancion(Cancion cancion) {
		// TODO Auto-generated method stub
		
	}

}
