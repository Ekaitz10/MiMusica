package com.example.demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.beans.Playlist;
import com.example.demo.beans.Usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.example.demo.beans.Artista;
import com.example.demo.beans.Cancion;

@Repository
public class GestorDB implements com.example.demo.interfaces.IGestorDB {

	private Connection conn;
	
	//conexion
	public GestorDB(@Value("${spring.datasource.url}")String URL,@Value( "${spring.datasource.driver-class-name}") String driver,@Value("${spring.datasource.username}") String usuario,@Value("${spring.datasource.password}") String contrasena) {
		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(URL,usuario, contrasena);
			System.err.println("conexion realizada");
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	public void crearUsuario(Usuario usuario) {
		try {
			Usuario usuarioanadir = new Usuario();
			usuarioanadir = usuario;
			PreparedStatement ps = conn.prepareStatement("insert into usuarios (nombre, apellido, edad) values (?,?,?)");
			ps.setString(1, usuarioanadir.getNombre());
			ps.setString(2, usuarioanadir.getApellido());
			ps.setInt(3, usuarioanadir.getEdad());
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public Usuario buscarUsuario(String nombre) {
		Usuario usuario = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios WHERE nombre = (?)");
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
				usuario.setEdad(rs.getInt("edad"));
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return usuario;
	}
	@Override
	public boolean existeUsuario(String nombre) {
		Usuario j = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios WHERE nombre = (?)");
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				j = new Usuario();
				j.setNombre(rs.getString("nombre"));
				j.setApellido(rs.getString("apellido"));
				j.setEdad(rs.getInt("edad"));
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        if(j != null) {
        	return true;
        }else {
        	return false;
        }
	}
	@Override
	public List<Usuario> todosLosUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
			
			while(rs.next()) {
				Usuario u = new Usuario();
				u.setNombre(rs.getString("nombre"));
				u.setApellido(rs.getString("apellido"));
				u.setEdad(Integer.parseInt(rs.getString("edad")));
				usuarios.add(u);
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
		return usuarios;
	}
	@Override
	public void eliminarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int buscarIdUsuario(String nombre) {
		int id = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT id FROM usuarios WHERE nombre = (?)");
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id");
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return id;
	}
	
	@Override
	public void crearPlaylist(Playlist playlist) {
		try {
			Playlist crearPlaylist = new Playlist();
			crearPlaylist = playlist;
			PreparedStatement ps = conn.prepareStatement("insert into playlists (nombre, usuario_id) values (?,?)");
			ps.setString(1, crearPlaylist.getNombre());
			ps.setInt(2, crearPlaylist.getId_usuario());
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	@Override
	public List<Playlist> todasLasPlaylists() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean existePlaylist(String nombre) {
		Playlist playlist = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios WHERE nombre = (?)");
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				playlist = new Playlist();
				playlist.setNombre(rs.getString("nombre"));
				playlist.setId_usuario(rs.getInt("usuario_id"));
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        if(playlist != null) {
        	return true;
        }else {
        	return false;
        }
	}
	@Override
	public void anadirCancion(Cancion cancion) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void eliminarPlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int buscarIdPlaylist(String nombre) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void crearArtista(Artista artista) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Artista> todosLosArtistas() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void eliminarArtista(Artista artista) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int buscarIdArtista(String nombre) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void crearCancion(Cancion cancion) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Cancion> todasLasCanciones() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Cancion> buscarCancion(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void eliminarCancion(Cancion cancion) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int buscarIdCancion(String nombre) {
		// TODO Auto-generated method stub
		return 0;
	}
	/*
	
	//actualizar
	//añadir jugador
	public void añadirJugador(Jugador jugador1) {
		try {
			Jugador jugadoranadir = new Jugador();
			jugadoranadir = jugador1;
			PreparedStatement ps = conn.prepareStatement("insert into jugadores (nombre, apellido, edad, equipo) values (?,?,?,?)");
			ps.setString(1, jugadoranadir.getNombre());
			ps.setString(2, jugadoranadir.getApellido());
			ps.setInt(3, jugadoranadir.getEdad());
			ps.setString(4, jugadoranadir.getEquipo());
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	//añadir equipo
	public void anadirEquipo(Equipo equipo1) {
		try {
			Equipo equipoanadir = new Equipo();
			equipoanadir = equipo1;
			PreparedStatement ps = conn.prepareStatement("insert into equipos (nombre, aÃ±ofundacion, jugadores, ciudad, titulos) values (?,?,?,?,?)");
			ps.setString(1, equipoanadir.getNombre());
			ps.setInt(2, equipoanadir.getAnofundacion());
			ps.setInt(3, equipoanadir.getJugadores());
			ps.setString(4, equipoanadir.getCiudad());
			ps.setInt(5, equipoanadir.getTitulos());
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	*/
}