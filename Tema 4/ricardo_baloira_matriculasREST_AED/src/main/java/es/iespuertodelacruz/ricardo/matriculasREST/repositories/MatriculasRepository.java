package es.iespuertodelacruz.ricardo.matriculasREST.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Matricula;

public interface MatriculasRepository extends JpaRepository<Matricula, Integer>{
}
