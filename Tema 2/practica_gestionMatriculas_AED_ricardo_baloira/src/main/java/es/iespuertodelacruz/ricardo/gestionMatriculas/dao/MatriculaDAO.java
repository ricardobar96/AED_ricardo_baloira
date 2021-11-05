package es.iespuertodelacruz.ricardo.gestionMatriculas.dao;

import java.util.List;

import es.iespuertodelacruz.ricardo.gestionMatriculas.modelo.Matricula;

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
		// TODO Auto-generated method stub
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
