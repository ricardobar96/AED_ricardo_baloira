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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.ricardo.matriculasREST.dto.MatriculaDTO;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Matricula;
import es.iespuertodelacruz.ricardo.matriculasREST.services.MatriculasService;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaREST {
	private Logger logger = LoggerFactory.getLogger(MatriculaREST.class);
	
	@Autowired
	MatriculasService matriculasService;
	
	@GetMapping
	public List<Matricula> getAll(){
		ArrayList<Matricula> matriculas = new ArrayList<Matricula>();
		matriculasService
		.findAll()
		.forEach(m -> matriculas.add((Matricula) m) );
		return matriculas;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMatriculaById(@PathVariable("id") Integer id) {
		
		Optional<Matricula> optMatricula = matriculasService.findById(id);
		if(optMatricula.isPresent()) {
			
			return ResponseEntity.ok(new MatriculaDTO(optMatricula.get()));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody MatriculaDTO matriculaDTO){
		Matricula m = new Matricula();
		m.setAlumno(matriculaDTO.getAlumno());
		m.setIdmatricula(matriculaDTO.getIdmatricula());
		m.setYear(matriculaDTO.getYear());

		matriculasService.save(m);
		
		return ResponseEntity.ok().body(new MatriculaDTO(m));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Optional<Matricula> optM = matriculasService.findById(id);
		if(optM.isPresent()) {
			matriculasService.deleteById(id);
			return ResponseEntity.ok("Matricula borrada");
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El registro de la matricula no existe");
		}

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MatriculaDTO mDTO){
		Optional<Matricula> optM = matriculasService.findById(id);
		if(optM.isPresent()) {
			Matricula m = optM.get();
			m.setAlumno(mDTO.getAlumno());
			m.setIdmatricula(mDTO.getIdmatricula());
			m.setYear(mDTO.getYear());
			
			return ResponseEntity.ok(matriculasService.save(m));
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El registro de la matricula no existe");
		}

	}	
}