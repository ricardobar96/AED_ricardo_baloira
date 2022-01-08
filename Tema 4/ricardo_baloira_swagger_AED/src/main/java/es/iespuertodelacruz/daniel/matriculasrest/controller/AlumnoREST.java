package es.iespuertodelacruz.daniel.matriculasrest.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

import es.iespuertodelacruz.daniel.matriculasrest.dto.AlumnoDTO;
import es.iespuertodelacruz.daniel.matriculasrest.dto.MatriculaDTO;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Alumno;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Asignatura;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Matricula;
import es.iespuertodelacruz.daniel.matriculasrest.service.AlumnoService;
import es.iespuertodelacruz.daniel.matriculasrest.service.AsignaturaService;
import es.iespuertodelacruz.daniel.matriculasrest.service.MatriculaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value= "AlumnoREST", description = "REST APIs relacionadas con las entidades Alumno y Matricula")
@RestController
@RequestMapping("/api/alumno")
public class AlumnoREST {
	//private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	AlumnoService alumnoService;
	@Autowired
	MatriculaService matriculaService;
	@Autowired
	AsignaturaService asignaturaService;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List l = new ArrayList<AlumnoDTO>();
		for(Alumno alumno : alumnoService.findAll()) {
			AlumnoDTO alumnoDto = new AlumnoDTO(alumno);
			l.add(alumnoDto);
		}
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getAlumnoById(@PathVariable("id") String id) {
		
		Optional<Alumno> optAlumno = alumnoService.findById(id);
		if(optAlumno.isPresent()) {
			
			return ResponseEntity.ok(optAlumno.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{idalu}/matricula/{idmatr}")
	public ResponseEntity<?> getMatriculaById(@PathVariable("idalu") String idAlu,
			@PathVariable("idmatr") Integer idMatr) {
		Optional<Alumno> optAlumno = alumnoService.findById(idAlu);
		if(optAlumno.isPresent()) {
			Optional<Matricula> optMatr = matriculaService.findById(idMatr);
			if (optMatr.isPresent()) {
				return ResponseEntity.ok(optMatr.get());
			} else {
				return ResponseEntity.notFound().build();
			}
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	@GetMapping("/{idalu}/matricula")
	public ResponseEntity<?> getAllMatriculas(@PathVariable("idalu") String idAlu) {
		Optional<Alumno> optAlumno = alumnoService.findById(idAlu);
		if(optAlumno.isPresent()) {
			List<Matricula> list = optAlumno.get().getMatriculas();
			return new ResponseEntity<>(list, HttpStatus.OK);
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@ApiOperation(value = "Borra un alumno por su DNI", tags = "delete")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id){
		Optional<Alumno> optM = alumnoService.findById(id);
		if(optM.isPresent()) {
			try {
				alumnoService.deleteById(id);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>("Hay matriculas relacionadas, elimínelas antes", HttpStatus.CONFLICT);
			}
			return ResponseEntity.ok("alumno borrado");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del registro no existe");
		}

	}
	
	@DeleteMapping("/{idAlu}/matricula/{idMatr}")
	public ResponseEntity<?> deleteMatricula(@PathVariable String idAlu, @PathVariable Integer idMatr){
		Optional<Alumno> optM = alumnoService.findById(idAlu);
		if(optM.isPresent()) {
			Optional<Matricula> optMatr = matriculaService.findById(idMatr);
			if (optMatr.isPresent()) {
				matriculaService.deleteById(idMatr);
				return ResponseEntity.ok("matricula borrada");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id de matricula no existe en el alumno");
			}
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del alumno no existe");
		}

	}
	
	@PostMapping("/{idalu}/matricula")
	public ResponseEntity<?> saveMatricula(@PathVariable("idalu") String idAlu, 
			@RequestBody MatriculaDTO matriculaDto) {
		Optional<Alumno> optAlumno = alumnoService.findById(idAlu);
		if(optAlumno.isPresent()) {
			Matricula matricula = new Matricula();
			matricula.setAlumno(optAlumno.get());
			//matricula.setAsignaturas(matriculaDto.getAsignaturas());
			ArrayList<Asignatura> asignaturas = new ArrayList<>();
			matricula.setYear(matriculaDto.getYear());
			if (matriculaDto.getAsignaturas() != null) {
				for (Asignatura asignatura : matriculaDto.getAsignaturas()) {
					Optional<Asignatura> optAsignatura = asignaturaService.findById(asignatura.getIdasignatura());
					if (optAsignatura.isPresent()) {
						asignaturas.add(optAsignatura.get());
					}
				}
			}
			matricula.setAsignaturas(asignaturas);
			MatriculaDTO matriculaSave = null;
			try {
				matriculaSave = new MatriculaDTO(matriculaService.save(matricula));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (matriculaSave != null) {
				return ResponseEntity.ok(matriculaSave);
			} else {
				return new ResponseEntity<>("Ya existe esa combinación de valores (Año, DNI)", HttpStatus.CONFLICT);
			}
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{idAlu}/matricula/{idMatr}")
	public ResponseEntity<?> updateMatricula(@PathVariable String idAlu, @PathVariable Integer idMatr, @RequestBody MatriculaDTO mDTO) {
		Optional<Alumno> optAlumno = alumnoService.findById(idAlu);
		if(optAlumno.isPresent()) {
			Optional<Matricula> optMatricula = matriculaService.findById(idMatr);
			if(optMatricula.isPresent()) {
				Matricula matricula = optMatricula.get();
				//matricula.setAsignaturas(matriculaDto.getAsignaturas());
				ArrayList<Asignatura> asignaturas = new ArrayList<>();
				matricula.setYear(mDTO.getYear());
				if (optMatricula.get().getAsignaturas() != null) {
					for (Asignatura asignatura : mDTO.getAsignaturas()) {
						Optional<Asignatura> optAsignatura = asignaturaService.findById(asignatura.getIdasignatura());
						if (optAsignatura.isPresent()) {
							asignaturas.add(optAsignatura.get());
						}
					}
				}
				matricula.setAsignaturas(asignaturas);
				MatriculaDTO matriculaSave = null;
				try {
					matriculaSave = new MatriculaDTO(matriculaService.save(matricula));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (matriculaSave != null) {
					return ResponseEntity.ok(matriculaSave);
				} else {
					return new ResponseEntity<>("Ya existe esa combinación de valores (Año, DNI)", HttpStatus.CONFLICT);
				}
			} else {
				return ResponseEntity.notFound().build();
			}
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> saveAlumno(
			@RequestBody AlumnoDTO alumnoDto) {
		Alumno alumno = new Alumno();
		alumno.setDni(alumnoDto.getDni());
		alumno.setNombre(alumnoDto.getNombre());
		alumno.setApellidos(alumnoDto.getApellidos());
		alumno.setFechanacimiento(alumnoDto.getFechaNacimiento());
		Alumno alumnoC = null;
		try {
			alumnoC = alumnoService.save(alumno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (alumnoC != null) {
			return new ResponseEntity<>(alumnoC, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Ya existe ese DNI", HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/{idalu}/matricula/year/{year}")
	public ResponseEntity<?> getMatriculaByYear(@PathVariable("idalu") String idAlu,
			@PathVariable("year") Integer year) {
		Optional<Alumno> optAlumno = alumnoService.findById(idAlu);
		if(optAlumno.isPresent()) {
			List<Matricula> matriculas = matriculaService.findByYear(year);
			if (matriculas != null) {
				return ResponseEntity.ok(matriculas);
			} else {
				return ResponseEntity.notFound().build();
			}
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id,
		@RequestBody Alumno aDTO){
		Optional<Alumno> optA = alumnoService.findById(id);
		if(optA.isPresent()) {
			Alumno a = optA.get();
			a.setNombre(aDTO.getNombre());
			a.setApellidos(aDTO.getApellidos());
			a.setFechanacimiento(aDTO.getFechanacimiento());
			return ResponseEntity.ok(alumnoService.save(a));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}
	
}
