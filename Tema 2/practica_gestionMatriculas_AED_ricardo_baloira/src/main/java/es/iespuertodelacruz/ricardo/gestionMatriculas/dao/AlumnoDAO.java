package es.iespuertodelacruz.ricardo.gestionMatriculas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.iespuertodelacruz.ricardo.gestionMatriculas.modelo.Alumno;

public class AlumnoDAO implements Crud<Alumno, String>{	
	GestorConexionDDBB gc;
	public AlumnoDAO(GestorConexionDDBB gc) {
	this.gc = gc;
	}
	
	@Override
	public Alumno findById(String dniBuscar) {
		Alumno alumno = null;
		String query = "SELECT dni, nombre, apellidos, fechanacimiento FROM alumnos WHERE dni = ?";	
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
				){
			
			ps.setString(1, dniBuscar);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				long nacimiento = rs.getLong("fechanacimiento");
				Date fecha = new Date(nacimiento);
				alumno = new Alumno(dni, nombre, apellidos, fecha);
			}			
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return alumno;
	}
	
	public List<Alumno> findByName(String nombreBuscar) {
		ArrayList<Alumno> alumnos = new ArrayList<>();
		String query = "SELECT dni, nombre, apellidos, fechanacimiento FROM alumnos WHERE nombre = ?";	
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
				){
			
			ps.setString(1, nombreBuscar);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				long nacimiento = rs.getLong("fechanacimiento");
				Date fecha = new Date(nacimiento);
				alumnos.add(new Alumno(dni, nombre, apellidos, fecha));
			}			
			
		} catch (SQLException e) {
				e.printStackTrace();
		} 
		return alumnos;
	}

	@Override
	public List<Alumno> findAll() {
		ArrayList<Alumno> alumnos = new ArrayList<>();
		String query = "SELECT dni, nombre, apellidos, fechanacimiento FROM alumnos";	
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();){
			while(rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				int nacimiento = rs.getInt("fechanacimiento");
				
				Date fechanacimiento = new Date(nacimiento);
				alumnos.add(new Alumno(dni, nombre, apellidos, fechanacimiento));
			}
			
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return alumnos;
	}

	@Override
	public Alumno save(Alumno obj) {
		String query = "INSERT INTO alumnos (dni, nombre, apellidos, fechanacimiento) VALUES (?, ?, ?, ?)";	
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);){
			ps.setString(1, obj.getDni());
			ps.setString(2, obj.getNombre());
			ps.setString(3, obj.getApellidos());
			if(obj.getFechanacimiento() != null) {
				ps.setLong(4, (long) obj.getFechanacimiento().getTime());
			}
			else {
				ps.setString(4, null);
			}
				
			ps.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return obj;
	}

	@Override
	public boolean update(Alumno obj) {
		int respuesta;
		boolean resultado = false;
		String query = "UPDATE alumnos SET nombre = ?, apellidos = ?, fechanacimiento = ? WHERE dni = ?";	
		try (Connection cn = gc.getConnection();
			PreparedStatement ps = cn.prepareStatement(query);){
			ps.setString(1, obj.getNombre());
			ps.setString(2, obj.getApellidos());
			
			if(obj.getFechanacimiento() != null) {
				ps.setLong(3, (long) obj.getFechanacimiento().getTime());
			}
			else{
				ps.setString(3, null);
			}
			
			ps.setString(4, obj.getDni());
	
			respuesta = ps.executeUpdate();
			if(respuesta>0) {
				resultado = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public boolean delete(String id) {
		int respuesta = 0;
		String dniAlumn = null;
		boolean resultado = false;
		String queryDelete = "DELETE FROM alumnos WHERE dni = ?";	
		String querySelect = "SELECT idmatricula FROM matriculas WHERE dni = ?";
		
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(querySelect);
				PreparedStatement ps2 = cn.prepareStatement(queryDelete);
				){
			ps.setString(1, id);
			ps2.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				dniAlumn = rs.getString("dni");
			}
			
			if(dniAlumn == null) {
				respuesta = ps2.executeUpdate();
			}

			if(respuesta>0) {
				resultado = true;
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
