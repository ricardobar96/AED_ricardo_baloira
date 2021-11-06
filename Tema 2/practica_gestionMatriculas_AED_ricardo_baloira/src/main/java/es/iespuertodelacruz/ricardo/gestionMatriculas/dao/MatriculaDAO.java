package es.iespuertodelacruz.ricardo.gestionMatriculas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import es.iespuertodelacruz.ricardo.gestionMatriculas.modelo.Matricula;
import es.iespuertodelacruz.ricardo.gestionMatriculas.modelo.Alumno;

public class MatriculaDAO implements Crud<Matricula, String>{
	GestorConexionDDBB gc;
	public MatriculaDAO(GestorConexionDDBB gc) {
	this.gc = gc;
	}
	@Override
	public Matricula findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Matricula> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Matricula save(Matricula obj) {
		String query = "INSERT INTO matriculas (dni, year) VALUES (?, ?)";	
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);){
				ps.setString(1, obj.getAlumno().getDni());
				ps.setInt(2, obj.getYear());

				ps.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean update(Matricula obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}
}
