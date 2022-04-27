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

import com.example.demo.beans.Usuario;
import com.example.demo.interfaces.IUsuarioRepository;

@Repository
public class JdbcUsuarioRepository implements IUsuarioRepository {

	private JdbcTemplate jdbc;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public JdbcUsuarioRepository(JdbcTemplate jdbc, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbc = jdbc;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	private Usuario mapRowToUsuario(ResultSet rs, int rowNum) throws SQLException {
		Usuario user = new Usuario();
		user.setNombre(rs.getString("nombre"));
		user.setApellido(rs.getString("apellido"));
		user.setEdad(rs.getInt("edad"));
		return user;
	}
	
	@Override
	public void crearUsuario(Usuario usuario) {
		jdbc.update("insert into usuarios (nombre, apellido, edad) values (?,?,?)",
				usuario.getNombre(),
				usuario.getApellido(),
				usuario.getEdad());
	}

	@Override
	public List<Usuario> todosLosUsuarios() {
		return (List) jdbc.query("SELECT * FROM usuarios", this::mapRowToUsuario);
	}
	
	@Override
	public Usuario buscarUsuario(String nombre) {
		return jdbc.query("SELECT * FROM usuarios WHERE nombre = ?", this::mapRowToUsuario, nombre).get(0);
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int buscarIdUsuario(String nombre) {
		int id;
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("nombre", nombre);
		int rs = namedParameterJdbcTemplate.queryForObject("SELECT id FROM usuarios WHERE nombre = :nombre",namedParameters, Integer.class);
		id = rs;
		return id;
	}


	@Override
	public boolean existeUsuario(String nombre) {
		Iterable<Usuario> resultado = jdbc.query("SELECT * FROM usuarios WHERE nombre = ?", this::mapRowToUsuario, nombre);
		Iterator<Usuario> it = resultado.iterator();
		if(it.hasNext()) {
        	return true;
        }else {
        	return false;
        }
	}

}
