package es.iespuertodelacruz.ricardo.gestionMatriculas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.ricardo.gestionMatriculas.modelo.Asignatura;

public class AsignaturaDAO implements Crud<Asignatura, String>{
	GestorConexionDDBB gc;
	public AsignaturaDAO(GestorConexionDDBB gc) {
	this.gc = gc;
	}
	@Override
	public Asignatura findById(String id) {
		Asignatura asignatura = null;
		String query = "SELECT idasignatura, nombre, curso FROM asignaturas WHERE idasignatura = ?";	
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();){
			
			int idBuscar = Integer.parseInt(id);
			ps.setInt(1, idBuscar);
			
			while(rs.next()) {
				int idasignatura = rs.getInt("idasignatura");
				String nombre = rs.getString("nombre");
				String curso = rs.getString("curso");
				asignatura = new Asignatura(idasignatura, nombre, curso);
			}			
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return asignatura;
	}
	@Override
	public List<Asignatura> findAll() {
		ArrayList<Asignatura> asignaturas = new ArrayList<>();
		String query = "SELECT idasignatura, nombre, curso FROM asignaturas";	
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();){

			while(rs.next()) {
				int idasignatura = rs.getInt("idasignatura");
				String nombre = rs.getString("nombre");
				String curso = rs.getString("curso");
				asignaturas.add(new Asignatura(idasignatura, nombre, curso));
			}			
			
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return asignaturas;
	}
	@Override
	public Asignatura save(Asignatura obj) {
		String query = "INSERT INTO asignaturas (idasignatura, nombre, curso) VALUES (?, ?, ?)";	
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);){
			ps.setInt(1, obj.getIdasignatura());
			ps.setString(2, obj.getNombre());
			ps.setString(3, obj.getCurso());
				
			ps.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return obj;
	}
	@Override
	public boolean update(Asignatura obj) {
		int respuesta;
		boolean resultado = false;
		String query = "UPDATE asignaturas SET idasignatura = ?, nombre = ?, curso = ? WHERE idasignatura = ?";	
		try (Connection cn = gc.getConnection();
			PreparedStatement ps = cn.prepareStatement(query);){
			ps.setInt(1, obj.getIdasignatura());
			ps.setString(2, obj.getNombre());
			ps.setString(3, obj.getCurso());
			ps.setInt(4, obj.getIdasignatura());
			
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
		String query = "DELETE FROM asignaturas WHERE idasignatura = ?";	
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);){
			int idBuscar = Integer.parseInt(id);
			ps.setInt(1, idBuscar);
				
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
