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
		AlumnoDAO alumnodao = new AlumnoDAO(gc);
		Matricula matricula = null;
		String dniAlumno = null;
		int respuesta = 0;
		ArrayList<Asignatura> asignaturas;
		String queryInsertMat = "INSERT INTO matriculas (dni, year) VALUES (?, ?)";	
		String querySelect = "SELECT dni FROM alumnos WHERE dni = ?";
		String queryInsertAsignMat = "INSERT INTO asignaturas_matriculas (idmatricula, idasignatura) VALUES (?, ?)";
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
				ResultSet rs = psInsertMat.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt(1);
				}	
				
				respuesta = psInsertMat.executeUpdate();
			}
			
			if(respuesta > 0) {
				asignaturas = obj.getAsignaturas();
				ResultSet rsAsignMat = psInsertAsignMat.getGeneratedKeys();
				for (Asignatura a : asignaturas) {
					if (rsAsignMat.next()) {
						id = rsAsignMat.getInt(1);
					}	
					psInsertAsignMat.setInt(1, obj.getIdmatricula());
					psInsertAsignMat.setInt(2, a.getIdasignatura());
					psInsertAsignMat.executeUpdate();
				}
				
				Alumno alumno  = alumnodao.findById(obj.getAlumno().getDni());
				matricula = new Matricula(id, alumno, obj.getYear());
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
