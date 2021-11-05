package es.iespuertodelacruz.ricardo.gestionMatriculas.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import es.iespuertodelacruz.ricardo.gestionMatriculas.modelo.Alumno;

public class AlumnoDAO implements Crud<Alumno, String>{
	
	GestorConexionDDBB gc;
	public AlumnoDAO(GestorConexionDDBB gc) {
	this.gc = gc;
	}
	
	@Override
	public Alumno findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alumno> findAll() {
		return null;
	}

	@Override
	public Alumno save(Alumno obj) {
		String query = "INSERT INTO alumnos (dni, nombre, apellidos, fechanacimiento, matriculas) VALUES (?, ?, ?, ?, ?)";	
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);){
				ps.setString(1, obj.getDni());
				ps.setString(2, obj.getNombre());
				ps.setString(3, obj.getApellidos());
				ps.setInt(4, (int) obj.getFechanacimiento().getTime());
				ps.setArray(5, (Array) obj.getMatriculas());
				
				ps.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Alumno obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}
}
