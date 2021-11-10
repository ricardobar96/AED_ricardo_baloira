package es.iespuertodelacruz.ricardo.gestionMatriculas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.ricardo.gestionMatriculas.modelo.Matricula;
import es.iespuertodelacruz.ricardo.gestionMatriculas.modelo.Alumno;
import es.iespuertodelacruz.ricardo.gestionMatriculas.modelo.Asignatura;

public class MatriculaDAO implements Crud<Matricula, String>{
	GestorConexionDDBB gc;
	public MatriculaDAO(GestorConexionDDBB gc) {
	this.gc = gc;
	}
	@Override
	public Matricula findById(String id) {
		Matricula matricula = null;
		AlumnoDAO alumnoDao = new AlumnoDAO(gc);
		Alumno alumno;
		String query = "SELECT idmatricula, dni, year FROM matriculas WHERE dni = ?";
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
				) {

			int idBuscar = Integer.parseInt(id);
			ps.setInt(1, idBuscar);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idmatricula = rs.getInt("idmatricula");
				alumno = alumnoDao.findById(id);
				int anio = rs.getInt("year");
				matricula = new Matricula(idmatricula, alumno, anio);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matricula;
	}
	@Override
	public List<Matricula> findAll() {
		ArrayList<Matricula> matriculas = new ArrayList<>();
		String query = "SELECT idmatricula, dni, year FROM matriculas";
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				) {

			while (rs.next()) {
				int idmatricula = rs.getInt("idmatricula");
				String dni = rs.getString("dni");
				int anio = rs.getInt("year");
				matriculas.add(new Matricula(idmatricula, new Alumno(dni), anio));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matriculas;
	}
	
	public List<Matricula> findByYear(String anio) {
		ArrayList<Matricula> matriculas = new ArrayList<>();
		AlumnoDAO alumnoDao = new AlumnoDAO(gc);
		Alumno alumno;
		String query = "SELECT idmatricula, dni, year FROM matriculas WHERE year = ?";
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
				) {
			int anioBuscar = Integer.parseInt(anio);
			ps.setInt(1, anioBuscar);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idmatricula = rs.getInt("idmatricula");				
				String dni = rs.getString("dni");
				alumno = alumnoDao.findById(dni);
				int year = rs.getInt("year");
				matriculas.add(new Matricula(idmatricula, alumno, year));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matriculas;
	}
	
	public List<Matricula> findByDni(String dni) {
		ArrayList<Matricula> matriculas = new ArrayList<>();
		AlumnoDAO alumnoDao = new AlumnoDAO(gc);
		Alumno alumno;
		String query = "SELECT idmatricula, dni, year FROM matriculas WHERE dni = ?";
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
				) {
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idmatricula = rs.getInt("idmatricula");
				alumno = alumnoDao.findById(dni);
				int anio = rs.getInt("year");
				matriculas.add(new Matricula(idmatricula, alumno, anio));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matriculas;
	}
	
	@Override
	public Matricula save(Matricula obj) {
		Matricula matricula = null;
		String query = "INSERT INTO matriculas (dni, year) VALUES (?, ?)";	
		//String queryINsertAsignMat = "INSERT INTO asignaturas_matriculas idmatricula";
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);){
			Integer id = null;
			
			ps.setString(1, obj.getAlumno().getDni());
			ps.setInt(2, obj.getYear());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}	
			
			AlumnoDAO alumnodao = new AlumnoDAO(gc);
			Alumno alumno  = alumnodao.findById(obj.getAlumno().getDni());
			matricula = new Matricula(id, alumno, obj.getYear());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	@Override
	public boolean update(Matricula obj) {
		int respuesta;
		boolean resultado = false;
		String query = "UPDATE matriculas SET dni = ?, year = ? WHERE idmatricula = ?";
		try (Connection cn = gc.getConnection(); PreparedStatement ps = cn.prepareStatement(query);) {
			ps.setString(1, obj.getAlumno().getDni());
			ps.setInt(2, obj.getYear());
			ps.setInt(3, obj.getIdmatricula());

			respuesta = ps.executeUpdate();
			if (respuesta > 0) {
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
		String query = "DELETE FROM matriculas WHERE idmatricula = ?";
		String queryDeleteAsignMat = "DELETE FROM asignatura_matricula  WHERE idmatricula = ?";
		try (Connection cn = gc.getConnection(); PreparedStatement ps = cn.prepareStatement(query);) {
			int idBuscar = Integer.parseInt(id);
			ps.setInt(1, idBuscar);

			respuesta = ps.executeUpdate();
			if (respuesta > 0) {
				resultado = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
