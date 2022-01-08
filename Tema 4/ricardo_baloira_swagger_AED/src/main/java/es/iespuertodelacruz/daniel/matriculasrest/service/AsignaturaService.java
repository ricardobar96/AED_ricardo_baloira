package es.iespuertodelacruz.daniel.matriculasrest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.daniel.matriculasrest.entity.Asignatura;
import es.iespuertodelacruz.daniel.matriculasrest.repository.AsignaturaRepository;

@Service
public class AsignaturaService implements GenericService<Asignatura, Integer> {

	@Autowired
	AsignaturaRepository asignaturaRepository;
	
	@Override
	public Iterable<Asignatura> findAll() {
		return asignaturaRepository.findAll();
	}

	@Override
	public Page<Asignatura> findAll(Pageable pageable) {
		return asignaturaRepository.findAll(pageable);
	}

	@Override
	public Optional<Asignatura> findById(Integer id) {
		return asignaturaRepository.findById(id);
	}

	@Override
	public Asignatura save(Asignatura object) {
		return asignaturaRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		asignaturaRepository.deleteById(id);
		
	}

	@Override
	public void delete(Asignatura object) {
		asignaturaRepository.delete(object);
	}

}
