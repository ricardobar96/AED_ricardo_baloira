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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.ricardo.matriculasREST.dto.AlumnoDTO;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Alumno;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Asignatura;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Matricula;
import es.iespuertodelacruz.ricardo.matriculasREST.services.AlumnosService;
import es.iespuertodelacruz.ricardo.matriculasREST.services.AsignaturasService;
import es.iespuertodelacruz.ricardo.matriculasREST.services.MatriculasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value= "MatriculaRESTv3", description = "REST APIs relacionadas con la entidad Alumno accesibles para el usuario con rol ADMIN")
@RestController
@RequestMapping("/api/v3/alumnos")
public class AlumnoRESTv3 {
	private Logger logger = LoggerFactory.getLogger(AlumnoRESTv3.class);
	
	@Autowired
	AlumnosService alumnosService;
	
	@Autowired
	MatriculasService matriculasService;
	
	@Autowired
	AsignaturasService asignaturasService;
	
	@GetMapping	
	@ApiOperation(value = "Devuelve lista de todos los alumnos", tags = "getAll")
	public Collection<AlumnoDTO> getAll(@RequestParam(required=false, name="nombre")String nombre){
		List alumnos = new ArrayList<Alumno>();
		
		if(nombre!=null) {
			alumnos.add(alumnosService.findByNombre(nombre));
		}
		else {
			for(Alumno a: alumnosService.findAll()) {
				alumnos.add(new AlumnoDTO(a));
				}
		}
		return alumnos;
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Busca un alumno por su DNI", tags = "get")
	@ApiImplicitParam(name = "id", value = "DNI del alumno", required = true, dataType = "string", paramType = "query")
	public ResponseEntity<?> getAlumnoById(@PathVariable("id") String id) {
		
		Optional<Alumno> optAlumno = alumnosService.findById(id);
		
		if(optAlumno.isPresent()) {
			
			return ResponseEntity.ok(new AlumnoDTO(optAlumno.get()));
		}else {
			return ResponseEntity.notFound().build();
		}		
	}
	
	@PostMapping
	@ApiOperation(value = "Crea un alumno", tags = "post")
	public ResponseEntity<?> save(@RequestBody AlumnoDTO alumnoDTO){
		Alumno a = new Alumno();
		if(alumnoDTO.getDni()!=null) {
			Optional<Alumno> optAlumno = alumnosService.findById(alumnoDTO.getDni());
			if(optAlumno.isPresent()){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un alumno con ese DNI");
			}
			
			else {
				a.setDni(alumnoDTO.getDni());
				a.setNombre(alumnoDTO.getNombre());
				a.setApellidos(alumnoDTO.getApellidos());
				a.setFechanacimiento(alumnoDTO.getFechanacimiento());
				
				alumnosService.save(a);
				return ResponseEntity.ok().body(a);
			}
		}
		
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debes especificar el DNI del alumno");
		}
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Borra un alumno por su DNI", tags = "delete")
	@ApiImplicitParam(name = "id", value = "DNI del alumno", required = true, dataType = "string", paramType = "query")
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
	@ApiOperation(value = "Modifica un alumno", tags = "put")
	@ApiImplicitParam(name = "id", value = "DNI del alumno", required = true, dataType = "string", paramType = "query")
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
