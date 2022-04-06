package com.example.demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.beans.Jugador;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.example.demo.beans.Equipo;

@Repository
public class GestorDB implements com.example.demo.interfaces.IGestorDB {

	private Connection conn;
	
	//conexion
	public GestorDB(@Value("${spring.datasource.url}") String URL, @Value("${spring.datasource.driver-class-name}")String driver, @Value("${spring.datasource.username}")String usuario, @Value("${spring.datasource.password}")String contraseña) {
		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(URL,usuario, contraseña);
			System.err.println("conexion realizada");
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	
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
	//añadir jugador
	public void añadirJugador(Jugador jugador1) {
		try {
			Jugador jugadorañadir = new Jugador();
			jugadorañadir = jugador1;
			PreparedStatement ps = conn.prepareStatement("insert into jugadores (nombre, apellido, edad, equipo) values (?,?,?,?)");
			ps.setString(1, jugadorañadir.getNombre());
			ps.setString(2, jugadorañadir.getApellido());
			ps.setInt(3, jugadorañadir.getEdad());
			ps.setString(4, jugadorañadir.getEquipo());
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	//añadir equipo
	public void añadirEquipo(Equipo equipo1) {
		try {
			Equipo equipoañadir = new Equipo();
			equipoañadir = equipo1;
			PreparedStatement ps = conn.prepareStatement("insert into equipos (nombre, añofundacion, jugadores, ciudad, titulos) values (?,?,?,?,?)");
			ps.setString(1, equipoañadir.getNombre());
			ps.setInt(2, equipoañadir.getAñofundacion());
			ps.setInt(3, equipoañadir.getJugadores());
			ps.setString(4, equipoañadir.getCiudad());
			ps.setInt(5, equipoañadir.getTitulos());
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
