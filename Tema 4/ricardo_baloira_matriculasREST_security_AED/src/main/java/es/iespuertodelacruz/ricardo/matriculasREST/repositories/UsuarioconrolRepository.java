package es.iespuertodelacruz.ricardo.matriculasREST.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Usuarioconrol;

public interface UsuarioconrolRepository extends JpaRepository<Usuarioconrol, Integer> {
    @Query("SELECT t FROM Usuarioconrol t where t.nombre = :name") 
    List<Usuarioconrol> findByNombre(@Param("name") String strNombre);
}
