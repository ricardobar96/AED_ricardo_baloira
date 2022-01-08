package es.iespuertodelacruz.daniel.matriculasrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.daniel.matriculasrest.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, String>{

}
