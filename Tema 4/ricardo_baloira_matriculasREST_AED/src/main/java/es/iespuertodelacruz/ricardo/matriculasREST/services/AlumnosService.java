package es.iespuertodelacruz.ricardo.matriculasREST.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Alumno;
import es.iespuertodelacruz.ricardo.matriculasREST.repositories.AlumnosRepository;

@Service
public class AlumnosService implements GenericService<Alumno,String>{

	@Autowired
	private AlumnosRepository alumnosRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Alumno> findAll() {
		// TODO Auto-generated method stub
		return alumnosRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Alumno> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return alumnosRepository.findAll(page);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Alumno> findById(String id) {
		// TODO Auto-generated method stub
		return alumnosRepository.findById(id);
	}

	@Override
	@Transactional
	public Alumno save(Alumno objeto) {
		// TODO Auto-generated method stub
		return alumnosRepository.save(objeto);
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		alumnosRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(Alumno entity) {
		// TODO Auto-generated method stub
		alumnosRepository.delete(entity);
	}
}
