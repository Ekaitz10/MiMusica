package com.example.demo.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.beans.Artista;
import com.example.demo.interfaces.IArtistaRepository;

@Repository
public class JdbcArtistaRepository implements IArtistaRepository{

	private JdbcTemplate jdbc;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public JdbcArtistaRepository(JdbcTemplate jdbc,NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbc = jdbc;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	private Artista mapRowToArtista(ResultSet rs, int rowNum) throws SQLException {
		Artista artista = new Artista();
		artista.setNombre(rs.getString("nombre"));
		artista.setNacionalidad(rs.getString("nacionalidad"));
		artista.setGenero(rs.getString("genero"));
		return artista;
	}
	
	@Override
	public void crearArtista(Artista artista) {
		jdbc.update("insert into artistas (nombre, nacionalidad, genero) values (?,?,?)",
				artista.getNombre(),
				artista.getNacionalidad(),
				artista.getGenero());
	}

	@Override
	public boolean existeArtista(String nombre) {
		Iterable<Artista> resultado = jdbc.query("SELECT * FROM artistas WHERE nombre = ?", this::mapRowToArtista, nombre);
		Iterator<Artista> it = resultado.iterator();
		if(it.hasNext()) {
        	return true;
        }else {
        	return false;
        }
	}

	@Override
	public List<Artista> todosLosArtistas() {
		return (List) jdbc.query("SELECT * FROM artistas", this::mapRowToArtista);
	}

	@Override
	public void eliminarArtista(Artista artista) {
		// TODO Auto-generated method stub
		
	}
	public Artista buscarArtista() {
		return null;
	}
	@Override
	public int buscarIdArtista(String nombre) {
		int id;
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("nombre", nombre);
		int rs = namedParameterJdbcTemplate.queryForObject("SELECT id FROM artistas WHERE nombre = :nombre",namedParameters, Integer.class);
		id = rs;
		return id;
	}

}
