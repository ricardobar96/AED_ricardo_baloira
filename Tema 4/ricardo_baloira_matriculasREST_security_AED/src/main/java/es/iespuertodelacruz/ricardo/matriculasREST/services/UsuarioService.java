package es.iespuertodelacruz.ricardo.matriculasREST.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Usuarioconrol;
import es.iespuertodelacruz.ricardo.matriculasREST.repositories.UsuarioconrolRepository;

@Service
public class UsuarioService implements GenericService<Usuarioconrol,Integer> {

	
	@Autowired
	UsuarioconrolRepository usuarioRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Iterable<Usuarioconrol> findAll() {
		
		return usuarioRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Usuarioconrol> findAll(Pageable page) {
		
		return usuarioRepository.findAll(page);
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Usuarioconrol> findById(Integer id) {
		
		return usuarioRepository.findById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public Usuarioconrol save(Usuarioconrol objeto) {

		return usuarioRepository.save(objeto);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(Integer id) {

		usuarioRepository.deleteById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Usuarioconrol entity) {

		usuarioRepository.delete(entity);
		
	}

	@Transactional(readOnly=true)
	public Usuarioconrol findByNombre(String nombre) {
		Usuarioconrol u = null;
		List<Usuarioconrol> lista = usuarioRepository.findByNombre(nombre);
		if( lista != null && lista.size() ==1)
			u = lista.get(0);
		return u;
	}
}

