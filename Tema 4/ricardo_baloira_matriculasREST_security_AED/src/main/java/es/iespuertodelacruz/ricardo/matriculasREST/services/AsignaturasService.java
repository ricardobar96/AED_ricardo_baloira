package es.iespuertodelacruz.ricardo.matriculasREST.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Asignatura;
import es.iespuertodelacruz.ricardo.matriculasREST.repositories.AsignaturasRepository;

@Service
public class AsignaturasService implements GenericService<Asignatura,Integer>{

	@Autowired
	AsignaturasRepository asignaturasRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Iterable<Asignatura> findAll() {
		// TODO Auto-generated method stub
		return asignaturasRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Asignatura> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return asignaturasRepository.findAll(page);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Asignatura> findById(Integer id) {
		// TODO Auto-generated method stub
		return asignaturasRepository.findById(id);
	}

	@Override
	@Transactional
	public Asignatura save(Asignatura objeto) {
		// TODO Auto-generated method stub
		return asignaturasRepository.save(objeto);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		asignaturasRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(Asignatura entity) {
		// TODO Auto-generated method stub
		asignaturasRepository.delete(entity);
	}
}