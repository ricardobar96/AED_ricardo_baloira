package es.iespuertodelacruz.daniel.matriculasrest.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericService <T,E> {
	Iterable<T> findAll();
	Page<T> findAll(Pageable pageable);
	Optional<T> findById(E id);
	T save(T object);
	void deleteById(E id);
	void delete(T object);
}
