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
	public Playlist buscarPlaylist(String nombre) {
		Playlist playlist = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlists WHERE nombre = (?)");
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				playlist = new Playlist();
				playlist.setNombre(rs.getString("nombre"));
				playlist.setId_usuario(rs.getInt("usuario_id"));
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return playlist;
	}
	public List<Playlist> todasLasPlaylists(int id) {
		List<Playlist> playlists = new ArrayList<Playlist>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlists WHERE usuario_id = (?)");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Playlist anadir = new Playlist();
				anadir.setNombre(rs.getString("nombre"));
				anadir.setId_usuario(rs.getInt("usuario_id"));
				playlists.add(anadir);
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
		return playlists;
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
	public void anadirCancion(int playlistid, int cancionid) {
		try {
			PreparedStatement ps = conn.prepareStatement("insert into cancion_playlist (playlist_id, cancion_id) values (?,?)");
			ps.setInt(1, playlistid);
			ps.setInt(2, cancionid);
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	@Override
	public void eliminarPlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int buscarIdPlaylist(String nombre) {
		int id = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT id FROM playlists WHERE nombre = (?)");
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

	public boolean existeArtista(String nombre) {
		Artista art = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM artistas WHERE nombre = (?)");
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				art = new Artista();
				art.setNombre(rs.getString("nombre"));
				art.setNacionalidad(rs.getString("nacionalidad"));
				art.setGenero(rs.getString("genero"));
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        if(art != null) {
        	return true;
        }else {
        	return false;
        }
	}
	@Override
	public void crearArtista(Artista artista) {
		try {
			Artista artistanadir = new Artista();
			artistanadir = artista;
			PreparedStatement ps = conn.prepareStatement("insert into artistas (nombre, nacionalidad, genero) values (?,?,?)");
			ps.setString(1, artistanadir.getNombre());
			ps.setString(2, artistanadir.getNacionalidad());
			ps.setString(3, artistanadir.getGenero());
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	@Override
	public List<Artista> todosLosArtistas() {
		List<Artista> artistas = new ArrayList<Artista>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM artistas");
			
			while(rs.next()) {
				Artista art = new Artista();
				art.setNombre(rs.getString("nombre"));
				art.setNacionalidad(rs.getString("nacionalidad"));
				art.setGenero(rs.getString("genero"));
				artistas.add(art);
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
		return artistas;
	}
	@Override
	public void eliminarArtista(Artista artista) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int buscarIdArtista(String nombre) {
		int id = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT id FROM artistas WHERE nombre = (?)");
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
	public void crearCancion(Cancion cancion) {
		try {
			Cancion cancionanadir = new Cancion();
			cancionanadir = cancion;
			PreparedStatement ps = conn.prepareStatement("insert into canciones (titulo, artista_id) values (?,?)");
			ps.setString(1, cancionanadir.getTitulo());
			ps.setInt(2, cancionanadir.getArtista_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	@Override
	public boolean existeCancion(String titulo) {
		Cancion cancion = null;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM canciones WHERE titulo = (?)");
			ps.setString(1, titulo);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				cancion = new Cancion();
				cancion.setTitulo(rs.getString("titulo"));
				cancion.setArtista_id(Integer.parseInt(rs.getString("artista_id")));
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        if(cancion != null) {
        	return true;
        }else {
        	return false;
        }
	}
	@Override
	public List<Cancion> todasLasCanciones(int playlist_id) {
		List<Cancion> canciones = new ArrayList<Cancion>();
		try {
			Statement stmt = conn.createStatement();
			PreparedStatement ps1 = conn.prepareStatement("SELECT cancion_id FROM cancion_playlist WHERE playlist_id = (?)");
			ps1.setInt(1, playlist_id);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next()) {
				PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM canciones WHERE id = (?)");
				ps2.setInt(1, rs1.getInt(1));
				ResultSet rs2 = ps2.executeQuery();
				if(rs2.next()) {
					Cancion cancion = new Cancion();
					cancion.setTitulo(rs2.getString("titulo"));
					cancion.setArtista_id(rs2.getInt("artista_id"));
					canciones.add(cancion);
		        }
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
		return canciones;
	}
	public List<Cancion> todasLasCanciones() {
		List<Cancion> canciones = new ArrayList<Cancion>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM canciones");
			
			while(rs.next()) {
				Cancion cancion = new Cancion();
				cancion.setTitulo(rs.getString("titulo"));
				cancion.setArtista_id(rs.getInt("artista_id"));
				canciones.add(cancion);
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
		return canciones;
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
	public int buscarIdCancion(String titulo) {
		int id = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT id FROM canciones WHERE titulo = (?)");
			ps.setString(1, titulo);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id");
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return id;
	}


}