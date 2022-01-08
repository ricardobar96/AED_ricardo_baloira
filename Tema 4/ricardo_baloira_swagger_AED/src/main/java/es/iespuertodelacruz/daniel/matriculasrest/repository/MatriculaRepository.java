package es.iespuertodelacruz.daniel.matriculasrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.daniel.matriculasrest.entity.Matricula;

public interface MatriculaRepository extends JpaRepository <Matricula, Integer> {
	@Query("SELECT t FROM Matricula t where t.year = :year")
	 List<Matricula> findByYear(@Param("year") Integer year);
}
