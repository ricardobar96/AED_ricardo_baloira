package es.iespuertodelacruz.ricardo.gestionMatriculas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public Alumno findById(String id) {
		Alumno alumno = null;
		String query = "SELECT dni, nombre, apellidos, fechanacimiento FROM alumnos WHERE dni = ?";	
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();){
			
			ps.setString(1, id);		
			while(rs.next()) {
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				Integer nacimiento = rs.getInt("fechanacimiento");
				SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
				Date fecha = originalFormat.parse(nacimiento.toString());

				alumno = new Alumno(dni, nombre, apellidos, (java.sql.Date) fecha);
			}			
			
		} catch (SQLException e) {
				e.printStackTrace();
		} catch (ParseException e) {
				e.printStackTrace();
		}
		return alumno;
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
				alumnos.add(new Alumno(dni, nombre, apellidos, (java.sql.Date)fechanacimiento));
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
			ps.setInt(4, (int) obj.getFechanacimiento().getTime());	
				
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
			ps.setInt(3, (int) obj.getFechanacimiento().getTime());
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
		int respuesta;
		boolean resultado = false;
		String query = "DELETE FROM alumnos WHERE dni = ?";	
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);){
			ps.setString(1, id);
				
			respuesta = ps.executeUpdate();
			if(respuesta>0) {
					resultado = true;
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
