package es.iespuertodelacruz.ricardo.matriculasREST.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Matricula;
import es.iespuertodelacruz.ricardo.matriculasREST.repositories.MatriculasRepository;

@Service
public class MatriculasService implements GenericService<Matricula,Integer>{

	@Autowired
	MatriculasRepository matriculasRepository;

	@Override
	@Transactional(readOnly=true)
	public Iterable<Matricula> findAll() {
		// TODO Auto-generated method stub
		return matriculasRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Matricula> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return matriculasRepository.findAll(page);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Matricula> findById(Integer id) {
		// TODO Auto-generated method stub
		return matriculasRepository.findById(id);
	}
	
	@Override
	@Transactional
	public Matricula save(Matricula objeto) {
		// TODO Auto-generated method stub
		return matriculasRepository.save(objeto);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		matriculasRepository.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(Matricula entity) {
		// TODO Auto-generated method stub
		matriculasRepository.delete(entity);
	}
}