package es.iespuertodelacruz.ricardo.gestionMatriculas.dao;

import java.util.List;

public interface Crud<T,E>{
	T findById(E id);
	List<T> findAll();
	T save(T obj);
	boolean update(T obj);
	boolean delete(E id);
}
