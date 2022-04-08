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
	public Usuario buscarUsuario(String nombre) {
		Usuario usuario = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios WHERE nombre = (?)");
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
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
	public void eliminarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
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
	public void anadirCancion(Cancion cancion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarPlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		
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
	/*
	//consultas
	@Override
	public List<Jugador> todosLosJugadores() {
		List<Jugador> jugadores = new ArrayList<Jugador>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM jugadores");
			
			while(rs.next()) {
				Jugador j = new Jugador();
				j.setNombre(rs.getString("nombre"));
				j.setApellido(rs.getString("apellido"));
				j.setEdad(Integer.parseInt(rs.getString("edad")));
				j.setEquipo(rs.getString("equipo"));
				jugadores.add(j);
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}
        
		return jugadores;
	}
	public List<Equipo> todosLosEquipos() {
		List<Equipo> equipos = new ArrayList<Equipo>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM equipos");
			
			while(rs.next()) {
				Equipo e = new Equipo();
				e.setNombre(rs.getString("nombre"));
				e.setAñofundacion(Integer.parseInt(rs.getString("añofundacion")));
				e.setJugadores(Integer.parseInt(rs.getString("jugadores")));
				e.setCiudad(rs.getString("ciudad"));
				e.setTitulos(Integer.parseInt(rs.getString("titulos")));
				equipos.add(e);
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}
		return equipos;
	}
	
	public List<Jugador> JugadorElegido(String nombre) {
		List<Jugador> jugadores = new ArrayList<Jugador>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM jugadores WHERE nombre = (?)");
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Jugador j = new Jugador();
				j.setNombre(rs.getString("nombre"));
				j.setApellido(rs.getString("apellido"));
				j.setEdad(rs.getInt("edad"));
				j.setEquipo(rs.getString("equipo"));
				jugadores.add(j);
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}
        
		return jugadores;
	}
	public List<Equipo> EquipoElegido(String nombre) {
		List<Equipo> equipos = new ArrayList<Equipo>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM equipos WHERE nombre = (?)");
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Equipo e = new Equipo();
				e.setNombre(rs.getString("nombre"));
				e.setAñofundacion(Integer.parseInt(rs.getString("añofundacion")));
				e.setJugadores(rs.getInt("jugadores"));
				e.setCiudad(rs.getString("ciudad"));
				e.setTitulos(rs.getInt("titulos"));
				equipos.add(e);
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}
        
		return equipos;
	}
	
	//actualizar
	//a�adir jugador
	public void a�adirJugador(Jugador jugador1) {
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
	
	//a�adir equipo
	public void anadirEquipo(Equipo equipo1) {
		try {
			Equipo equipoanadir = new Equipo();
			equipoanadir = equipo1;
			PreparedStatement ps = conn.prepareStatement("insert into equipos (nombre, añofundacion, jugadores, ciudad, titulos) values (?,?,?,?,?)");
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
