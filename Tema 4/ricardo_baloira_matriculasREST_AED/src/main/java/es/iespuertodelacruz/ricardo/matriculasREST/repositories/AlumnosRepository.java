package es.iespuertodelacruz.ricardo.matriculasREST.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Alumno;

public interface AlumnosRepository extends JpaRepository<Alumno, String>{

}
