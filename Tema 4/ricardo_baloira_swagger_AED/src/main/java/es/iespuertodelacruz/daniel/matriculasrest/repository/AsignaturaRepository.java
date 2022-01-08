package es.iespuertodelacruz.daniel.matriculasrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.daniel.matriculasrest.entity.Asignatura;

public interface AsignaturaRepository extends JpaRepository <Asignatura, Integer> {

}
