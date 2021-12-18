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

import es.iespuertodelacruz.ricardo.matriculasREST.dto.AlumnoDTO;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Alumno;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Asignatura;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Matricula;
import es.iespuertodelacruz.ricardo.matriculasREST.services.AlumnosService;
import es.iespuertodelacruz.ricardo.matriculasREST.services.AsignaturasService;
import es.iespuertodelacruz.ricardo.matriculasREST.services.MatriculasService;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoREST {
	private Logger logger = LoggerFactory.getLogger(AlumnoREST.class);
	
	@Autowired
	AlumnosService alumnosService;
	
	@Autowired
	MatriculasService matriculasService;
	
	@Autowired
	AsignaturasService asignaturasService;
	
	@GetMapping	
	public Collection<AlumnoDTO> getAll(){
		List alumnos = new ArrayList<Alumno>();
		for(Alumno a: alumnosService.findAll()) {
			alumnos.add(new AlumnoDTO(a));
		}
		return alumnos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAlumnoById(@PathVariable("id") String id) {
		
		Optional<Alumno> optAlumno = alumnosService.findById(id);
		
		if(optAlumno.isPresent()) {
			
			return ResponseEntity.ok(new AlumnoDTO(optAlumno.get()));
		}else {
			return ResponseEntity.notFound().build();
		}		
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody AlumnoDTO alumnoDTO){
		Alumno a = new Alumno();
		a.setDni(alumnoDTO.getDni());
		a.setNombre(alumnoDTO.getNombre());
		a.setApellidos(alumnoDTO.getApellidos());
		a.setFechanacimiento(alumnoDTO.getFechanacimiento());
		//a.setMatriculas(null);
		
		alumnosService.save(a);
		
		return ResponseEntity.ok().body(new AlumnoDTO(a));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id){
		Optional<Alumno> optAlumno = alumnosService.findById(id);
		if(optAlumno.isPresent()) {
			for (Matricula m : optAlumno.get().getMatriculas()) {
				for (Asignatura a : m.getAsignaturas()) {
					a.getMatriculas().remove(m);
				}
				matriculasService.delete(m);
			}
			alumnosService.deleteById(id);
			return ResponseEntity.ok("Alumno borrado");
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El registro del alumno no existe");
		}

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody AlumnoDTO alumnoDTO){
		Optional<Alumno> optA = alumnosService.findById(id);
		if(optA.isPresent()) {
			Alumno a = optA.get();
			
			if(alumnoDTO.getNombre()!=null) {
				a.setNombre(alumnoDTO.getNombre());
			}
			
			if(alumnoDTO.getApellidos()!=null) {
				a.setApellidos(alumnoDTO.getApellidos());
			}
			
			if(alumnoDTO.getFechanacimiento()!=null) {
				a.setFechanacimiento(alumnoDTO.getFechanacimiento());
			}
			
			return ResponseEntity.ok(alumnosService.save(a));
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El registro del alumno no existe");
		}
	}
}