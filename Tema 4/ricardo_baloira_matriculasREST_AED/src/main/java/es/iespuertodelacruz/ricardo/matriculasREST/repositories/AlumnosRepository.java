package es.iespuertodelacruz.ricardo.matriculasREST.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Alumno;

public interface AlumnosRepository extends JpaRepository<Alumno, String>{

	Optional<Alumno> findByNombre(String nombre);
	
}
