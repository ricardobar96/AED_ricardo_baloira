package es.iespuertodelacruz.ricardo.matriculasREST.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Asignatura;
import es.iespuertodelacruz.ricardo.matriculasREST.services.AsignaturasService;

@RestController
@RequestMapping("/api/v1/asignaturas")
public class AsignaturaRESTv1 {
	private Logger logger = LoggerFactory.getLogger(AsignaturaRESTv1.class);
	@Autowired
	AsignaturasService asignaturasService;
	@GetMapping("")
	public List<Asignatura> getAll(){
		ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
		asignaturasService
		.findAll()
		.forEach(a -> asignaturas.add((Asignatura) a) );
		return asignaturas;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAsignaturaById(@PathVariable Integer id){
		Optional<Asignatura> asignaturaOPT = asignaturasService.findById(id);
		if (asignaturaOPT.isPresent()) {
			return ResponseEntity.ok(asignaturaOPT);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La asignatura no existe");
		}
		
	}
}
