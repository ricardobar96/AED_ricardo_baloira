package es.iespuertodelacruz.ricardo.matriculasREST.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Alumno;
import es.iespuertodelacruz.ricardo.matriculasREST.repositories.AlumnosRepository;

public class AlumnosService implements GenericService<Alumno,String>{

	@Autowired
	AlumnosRepository alumnosRepository;
	
	@Override
	public Iterable<Alumno> findAll() {
		// TODO Auto-generated method stub
		return alumnosRepository.findAll();
	}

	@Override
	public Page<Alumno> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return alumnosRepository.findAll(page);
	}

	@Override
	public Optional<Alumno> findById(String id) {
		// TODO Auto-generated method stub
		return alumnosRepository.findById(id);
	}

	@Override
	public Alumno save(Alumno objeto) {
		// TODO Auto-generated method stub
		return alumnosRepository.save(objeto);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		alumnosRepository.deleteById(id);
	}

	@Override
	public void delete(Alumno entity) {
		// TODO Auto-generated method stub
		alumnosRepository.delete(entity);
	}
}
