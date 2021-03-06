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
		ArrayList<Asignatura> asignaturas = new ArrayList<>();
		ArrayList<Integer> idAsign = new ArrayList<>();
		AlumnoDAO alumnoDao = new AlumnoDAO(gc);
		AsignaturaDAO asignaturaDao = new AsignaturaDAO(gc);
		Alumno alumno;
		String query = "SELECT idmatricula, dni, year FROM matriculas WHERE idmatricula = ?";
		String querySelectAsign = "SELECT idasignatura FROM asignatura_matricula WHERE idmatricula = ?";
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
				PreparedStatement psSelectAsign = cn.prepareStatement(querySelectAsign);
				) {

			int idBuscar = Integer.parseInt(id);
			ps.setInt(1, idBuscar);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idmatricula = rs.getInt("idmatricula");
				alumno = alumnoDao.findById(id);
				int anio = rs.getInt("year");
				
				psSelectAsign.setInt(1, idmatricula);
				ResultSet rsSelectAsign = psSelectAsign.executeQuery();
				while(rsSelectAsign.next()) {
					int idAsignatura = rsSelectAsign.getInt("idasignatura");
					idAsign.add(idAsignatura);
				}
				for (int asign : idAsign) {
					asignaturas.add(asignaturaDao.findById(String.valueOf(asign)));
				}
				
				matricula = new Matricula(idmatricula, alumno, anio, asignaturas);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matricula;
	}
	@Override
	public List<Matricula> findAll() {
		AlumnoDAO alumnoDao = new AlumnoDAO(gc);
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
				Alumno alumno = alumnoDao.findById(dni);
				matriculas.add(new Matricula(idmatricula, alumno, anio));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matriculas;
	}
	
	public List<Matricula> findByYear(String anio) {
		ArrayList<Matricula> matriculas = new ArrayList<>();
		AlumnoDAO alumnoDao = new AlumnoDAO(gc);
		MatriculaDAO matriculaDao = new MatriculaDAO(gc);
		
		Alumno alumno;
		Matricula matricula;
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
				matricula = matriculaDao.findById(String.valueOf(idmatricula));
				int year = rs.getInt("year");
				matriculas.add(new Matricula(idmatricula, alumno, year, matricula.getAsignaturas()));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matriculas;
	}
	
	public List<Matricula> findByDni(String dni) {
		ArrayList<Matricula> matriculas = new ArrayList<>();
		MatriculaDAO matriculaDao = new MatriculaDAO(gc);
		AlumnoDAO alumnoDao = new AlumnoDAO(gc);
		Alumno alumno;
		Matricula matricula;
		String query = "SELECT idmatricula, dni, year FROM matriculas WHERE dni = ?";
		try (Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
				) {
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idmatricula = rs.getInt("idmatricula");
				matricula = matriculaDao.findById(String.valueOf(idmatricula));
				alumno = alumnoDao.findById(dni);
				int anio = rs.getInt("year");
				matriculas.add(new Matricula(idmatricula, alumno, anio, matricula.getAsignaturas()));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matriculas;
	}
	
	@Override
	public Matricula save(Matricula obj) {
		Matricula matricula = null;
		String dniAlumno = null;
		int respuesta = 0;
		ArrayList<Asignatura> asignaturas;
		String queryInsertMat = "INSERT INTO matriculas (dni, year) VALUES (?, ?)";	
		String querySelect = "SELECT dni FROM alumnos WHERE dni = ?";
		String queryInsertAsignMat = "INSERT INTO asignatura_matricula (idmatricula, idasignatura) VALUES (?, ?)";
		try (Connection cn = gc.getConnection();
				PreparedStatement psSelect = cn.prepareStatement(querySelect);
				PreparedStatement psInsertAsignMat = 
						cn.prepareStatement(queryInsertAsignMat, PreparedStatement.RETURN_GENERATED_KEYS);
				PreparedStatement psInsertMat = 
						cn.prepareStatement(queryInsertMat, PreparedStatement.RETURN_GENERATED_KEYS);){
			cn.setAutoCommit(false);
			
			Integer id = null;
			
			psInsertMat.setString(1, obj.getAlumno().getDni());
			psInsertMat.setInt(2, obj.getYear());
			psSelect.setString(1, obj.getAlumno().getDni());
			
			ResultSet rsSelectDni = psSelect.executeQuery();
			while(rsSelectDni.next()) {
				dniAlumno = rsSelectDni.getString("dni");
			}
			
			if(dniAlumno != null) {
				
				respuesta = psInsertMat.executeUpdate();
				
				ResultSet rs = psInsertMat.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt(1);
				}	
						
			}
			
			if(respuesta > 0) {
				asignaturas = obj.getAsignaturas();
				for (Asignatura a : asignaturas) {
					psInsertAsignMat.setInt(1, id);
					psInsertAsignMat.setInt(2, a.getIdasignatura());
					respuesta = psInsertAsignMat.executeUpdate();
				}
				
			}
			
			if(respuesta > 0) {
				matricula = new Matricula(id, obj.getAlumno(), obj.getYear(), obj.getAsignaturas());
				cn.commit();
				cn.setAutoCommit(true);
			}
			
			else {
				cn.rollback();
				cn.setAutoCommit(true);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matricula;
	}
	
	@Override
	public boolean update(Matricula obj) {
		ArrayList<Asignatura> asignaturas;
		int respuesta;
		boolean resultado = false;
		String queryDelete = "DELETE FROM asignatura_matricula WHERE idmatricula = ?";
		String queryUpdate = "UPDATE matriculas SET dni= ?, year= ? WHERE idmatricula = ?";
		String queryInsert = "INSERT INTO asignatura_matricula (idasignatura,idmatricula) VALUES (?, ?)";
		try (Connection cn = gc.getConnection(); 
				PreparedStatement psDelete = cn.prepareStatement(queryDelete);
				PreparedStatement psUpdate = cn.prepareStatement(queryUpdate);
				PreparedStatement psInsert = cn.prepareStatement(queryInsert);
				) {
			cn.setAutoCommit(false);
			
			psDelete.setInt(1, obj.getIdmatricula());
			respuesta = psDelete.executeUpdate();
			
			psUpdate.setString(1, obj.getAlumno().getDni());
			psUpdate.setInt(2, obj.getYear());
			psUpdate.setInt(3, obj.getIdmatricula());
			
			respuesta = psUpdate.executeUpdate();
			
			if (respuesta > 0) {
				
				asignaturas = obj.getAsignaturas();
				for (Asignatura a : asignaturas) {
					psInsert.setInt(1, a.getIdasignatura());
					psInsert.setInt(2, obj.getIdmatricula());
					psInsert.executeUpdate();
				}

				cn.commit();
				cn.setAutoCommit(true);
				resultado = true;
			}
			else{
				cn.rollback();
				cn.setAutoCommit(true);
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
		String queryDeleteMat = "DELETE FROM matriculas WHERE idmatricula = ?";
		String queryDeleteAsignMat = "DELETE FROM asignatura_matricula  WHERE idmatricula = ?";
		try (Connection cn = gc.getConnection(); 
				PreparedStatement psDeleteMat = cn.prepareStatement(queryDeleteMat);
				PreparedStatement psDeleteAsignMat = cn.prepareStatement(queryDeleteAsignMat);) {
			
			cn.setAutoCommit(false);
			
			int idBuscar = Integer.parseInt(id);
			
			psDeleteAsignMat.setInt(1, idBuscar);
			psDeleteMat.setInt(1, idBuscar);

			respuesta = psDeleteAsignMat.executeUpdate();
			if (respuesta > 0) {
				respuesta = psDeleteMat.executeUpdate();
			}
			
			if(respuesta > 0) {
				cn.commit();
				cn.setAutoCommit(true);
				resultado = true;
			}
			
			else{
				cn.rollback();
				cn.setAutoCommit(true);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
