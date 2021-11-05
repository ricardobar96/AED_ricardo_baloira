package es.iespuertodelacruz.ricardo.gestionMatriculas.dao;

import java.util.List;

import es.iespuertodelacruz.ricardo.gestionMatriculas.modelo.Asignatura;

public class AsignaturaDAO implements Crud<Asignatura, String>{
	GestorConexionDDBB gc;
	public AsignaturaDAO(GestorConexionDDBB gc) {
	this.gc = gc;
	}
	@Override
	public Asignatura findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Asignatura> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Asignatura save(Asignatura obj) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean update(Asignatura obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}
}
