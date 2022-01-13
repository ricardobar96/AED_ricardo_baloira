package es.iespuertodelacruz.ricardo.matriculasREST.controller;

import java.util.ArrayList;
import java.util.Collection;
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
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Alumno;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Asignatura;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Matricula;
import es.iespuertodelacruz.ricardo.matriculasREST.services.AlumnosService;
import es.iespuertodelacruz.ricardo.matriculasREST.services.AsignaturasService;
import es.iespuertodelacruz.ricardo.matriculasREST.services.MatriculasService;

@RestController
@RequestMapping("/api/v3/matriculas")
public class MatriculaRESTv3 {
	private Logger logger = LoggerFactory.getLogger(MatriculaRESTv3.class);
	
	@Autowired
	MatriculasService matriculasService;
	
	@Autowired
	AlumnosService alumnosService;
	
	@Autowired
	AsignaturasService asignaturasService;
	
	@GetMapping	
	public Collection<MatriculaDTO> getAll(){
		List matriculas = new ArrayList<Matricula>();
		for(Matricula m: matriculasService.findAll()) {
			matriculas.add(new MatriculaDTO(m));
		}
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
		if(matriculaDTO.getAlumno()!=null) {
			Optional<Alumno> optAlumno = alumnosService.findById(matriculaDTO.getAlumno().getDni());
			
			if(optAlumno.isPresent()) {
				m.setAlumno(optAlumno.get());
				m.setYear(matriculaDTO.getYear());	
				
				for (Asignatura a : matriculaDTO.getAsignaturas()) {
					Optional<Asignatura> optAsig = asignaturasService.findById(a.getIdasignatura());
					optAsig.get().getMatriculas().add(m);
				}			
				m.setAsignaturas(matriculaDTO.getAsignaturas());
				
				matriculasService.save(m);
				return ResponseEntity.ok().body(m);
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El alumno especificado no existe");
			}
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debes especificar un alumno para la matricula");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Optional<Matricula> optM = matriculasService.findById(id);
		if(optM.isPresent()) {
			for (Asignatura a : optM.get().getAsignaturas()) {
				a.getMatriculas().remove(optM.get());
			}
			matriculasService.deleteById(id);
			return ResponseEntity.ok("Matricula borrada");
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El registro de la matricula no existe");
		}

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody MatriculaDTO matriculaDTO){
		Optional<Matricula> optM = matriculasService.findById(id);
		if(optM.isPresent()) {
			Matricula m = optM.get();
			
			if(matriculaDTO.getYear()>0) {
				m.setYear(matriculaDTO.getYear());
			}
			
			if(matriculaDTO.getAsignaturas()!=null) {
				
				for (Asignatura a : optM.get().getAsignaturas()) {
					a.getMatriculas().remove(optM.get());
				}
				
				for (Asignatura a : matriculaDTO.getAsignaturas()) {
					Optional<Asignatura> optAsig = asignaturasService.findById(a.getIdasignatura());
					optAsig.get().getMatriculas().add(m);
				}			
				m.setAsignaturas(matriculaDTO.getAsignaturas());
				
			}
			
			return ResponseEntity.ok(matriculasService.save(m));
				
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El registro de la matricula no existe");
		}
	}	
}