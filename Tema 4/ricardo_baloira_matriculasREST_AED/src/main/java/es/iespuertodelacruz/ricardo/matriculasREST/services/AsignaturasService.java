package es.iespuertodelacruz.ricardo.matriculasREST.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Asignatura;
import es.iespuertodelacruz.ricardo.matriculasREST.repositories.AsignaturasRepository;

public class AsignaturasService implements GenericService<Asignatura,Integer>{

	@Autowired
	AsignaturasRepository asignaturasRepository;
	
	@Override
	public Iterable<Asignatura> findAll() {
		// TODO Auto-generated method stub
		return asignaturasRepository.findAll();
	}

	@Override
	public Page<Asignatura> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return asignaturasRepository.findAll(page);
	}

	@Override
	public Optional<Asignatura> findById(Integer id) {
		// TODO Auto-generated method stub
		return asignaturasRepository.findById(id);
	}

	@Override
	public Asignatura save(Asignatura objeto) {
		// TODO Auto-generated method stub
		return asignaturasRepository.save(objeto);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		asignaturasRepository.deleteById(id);
	}

	@Override
	public void delete(Asignatura entity) {
		// TODO Auto-generated method stub
		asignaturasRepository.delete(entity);
	}
}
