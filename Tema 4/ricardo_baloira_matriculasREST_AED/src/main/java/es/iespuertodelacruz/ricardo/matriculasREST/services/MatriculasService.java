package es.iespuertodelacruz.ricardo.matriculasREST.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Matricula;
import es.iespuertodelacruz.ricardo.matriculasREST.repositories.MatriculasRepository;

public class MatriculasService implements GenericService<Matricula,Integer>{

	@Autowired
	MatriculasRepository matriculasRepository;

	@Override
	public Iterable<Matricula> findAll() {
		// TODO Auto-generated method stub
		return matriculasRepository.findAll();
	}

	@Override
	public Page<Matricula> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return matriculasRepository.findAll(page);
	}

	@Override
	public Optional<Matricula> findById(Integer id) {
		// TODO Auto-generated method stub
		return matriculasRepository.findById(id);
	}
	@Override
	public Matricula save(Matricula objeto) {
		// TODO Auto-generated method stub
		return matriculasRepository.save(objeto);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		matriculasRepository.deleteById(id);
	}

	@Override
	public void delete(Matricula entity) {
		// TODO Auto-generated method stub
		matriculasRepository.delete(entity);
	}
}
