package es.iespuertodelacruz.daniel.matriculasrest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.daniel.matriculasrest.entity.Matricula;
import es.iespuertodelacruz.daniel.matriculasrest.repository.MatriculaRepository;

@Service
public class MatriculaService implements GenericService<Matricula, Integer> {

	@Autowired
	MatriculaRepository matriculaRepository;
	
	@Override
	public Iterable<Matricula> findAll() {
		return matriculaRepository.findAll();
	}

	@Override
	public Page<Matricula> findAll(Pageable pageable) {
		return matriculaRepository.findAll(pageable);
	}

	@Override
	public Optional<Matricula> findById(Integer id) {
		return matriculaRepository.findById(id);
	}

	@Override
	public Matricula save(Matricula object) {
		return matriculaRepository.save(object);
	}

	@Override
	public void deleteById(Integer id) {
		matriculaRepository.deleteById(id);
		
	}
	
	@Override
	public void delete(Matricula object) {
		matriculaRepository.delete(object);
	}
	
	@Transactional(readOnly=true)
	public List<Matricula> findByYear(Integer year) {
	return ((MatriculaRepository)matriculaRepository).findByYear(year);
	}

}
