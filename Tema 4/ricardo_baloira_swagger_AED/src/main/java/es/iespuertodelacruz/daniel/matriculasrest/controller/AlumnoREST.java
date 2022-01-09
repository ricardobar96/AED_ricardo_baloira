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
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
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
	
	@ApiOperation(value = "Devuelve una lista de AlumnosDTO", 
		    response = AlumnoDTO.class,
		    responseContainer = "List")
	@GetMapping
	public ResponseEntity<?> getAll(){
		List l = new ArrayList<AlumnoDTO>();
		for(Alumno alumno : alumnoService.findAll()) {
			AlumnoDTO alumnoDto = new AlumnoDTO(alumno);
			l.add(alumnoDto);
		}
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
	@ApiOperation(value = "Devuelve un Alumno por su DNI", 
		    response = Alumno.class)
	@GetMapping("/{id}")
	public ResponseEntity<?> getAlumnoById(
			@ApiParam(value = "DNI del alumno al que encontrar", required = true)
			@PathVariable("id") String id) {
		
		Optional<Alumno> optAlumno = alumnoService.findById(id);
		if(optAlumno.isPresent()) {
			
			return ResponseEntity.ok(optAlumno.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ApiOperation(value = "Devuelve una Matricula por su id", 
		    response = Matricula.class)
	@GetMapping("/{idalu}/matricula/{idmatr}")
	public ResponseEntity<?> getMatriculaById(
			@ApiParam(value = "DNI del alumno dueño de la Matricula", required = true)
			@PathVariable("idalu") String idAlu,
			@ApiParam(value = "Id de la Matricula a encontrar", required = true)
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
	
	
	@ApiOperation(value = "Devuelve una lista de Matricula", 
		    response = Matricula.class,
		    responseContainer = "List")
	@GetMapping("/{idalu}/matricula")
	public ResponseEntity<?> getAllMatriculas(
			@ApiParam(value = "DNI del alumno dueño de la lista de Matricula que queremos", required = true)
			@PathVariable("idalu") String idAlu) {
		Optional<Alumno> optAlumno = alumnoService.findById(idAlu);
		if(optAlumno.isPresent()) {
			List<Matricula> list = optAlumno.get().getMatriculas();
			return new ResponseEntity<>(list, HttpStatus.OK);
			
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@ApiOperation(value = "Borra un alumno si existe y si no tiene matrículas asignadas", 
		    response = String.class,
		    notes = "Devolvemos un String que diga si se borró o no el Alumno")
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
	
	@ApiOperation(value = "Borra una matricula", 
		    response = String.class,
		    notes = "Devolvemos un String que diga si se borró o no la Matricula")
	@DeleteMapping("/{idAlu}/matricula/{idMatr}")
	public ResponseEntity<?> deleteMatricula(
			@ApiParam(value = "DNI del alumno dueño de la matrícula a eliminar", required = true)
			@PathVariable String idAlu, 
			@PathVariable Integer idMatr){
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
	
	@ApiOperation(value = "Guarda una matricula", 
		    response = Matricula.class,
		    notes = "Devolvemos un String que diga si hay algun problema, y la Matricula si la guardamos con éxito")
	@ApiResponses(value = { 
			  @ApiResponse(code = 409, message = "Ya existe esa combinación de valores (Año, DNI)"),
		      @ApiResponse(code = 404, message = "No se encuentra el alumno."), 
		      @ApiResponse(code = 200, message = "Se ha guardado la Matricula.") })
	@PostMapping("/{idalu}/matricula")
	public ResponseEntity<?> saveMatricula(
			@ApiParam(value = "DNI del alumno dueño de la matrícula a añadir", required = true)
			@PathVariable("idalu") String idAlu, 
			@ApiParam(value = "Matricula que queremos guardar en la base de datos", required = true)
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
	
	@ApiOperation(value = "Actualiza una matricula", 
		    response = Matricula.class,
		    notes = "Devolvemos un String que diga si hay algun problema, y la Matricula si la guardamos con éxito")
	@PutMapping("/{idAlu}/matricula/{idMatr}")
	public ResponseEntity<?> updateMatricula(
			@ApiParam(value = "DNI del alumno dueño de la matrícula a modificar", required = true)
			@PathVariable String idAlu, 
			@ApiParam(value = "Id de la Matricula a modificar", required = true)
			@PathVariable Integer idMatr, 
			@ApiParam(value = "Matricula nueva con los atributos a sustituir de la antigua", required = true)
			@RequestBody MatriculaDTO mDTO) {
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
	
	
	@ApiOperation(value = "Guarda un alumno", 
		    response = Alumno.class,
		    notes = "Devolvemos un String que diga si hay algun problema, y el alumno si lo guardamos con éxito")
	@PostMapping
	public ResponseEntity<?> saveAlumno(
			@ApiParam(value = "Alumno nuevo que agregar", required = true)
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
	
	@ApiOperation(value = "Devuelve una lista de Matricula por su año de un alumno.", 
		    response = Alumno.class,
		    responseContainer="List")
	@GetMapping("/{idalu}/matricula/year/{year}")
	public ResponseEntity<?> getMatriculaByYear(
			@ApiParam(value = "DNI del alumno en el que buscar.", required = true)
			@PathVariable("idalu") String idAlu,
			@ApiParam(value = "Año que buscar", required = true)
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
	
	@ApiOperation(value = "Actualiza la informacion de un alumno.", 
		    response = Alumno.class)
	@PutMapping("/{id}")
	public ResponseEntity<?> update(
			@ApiParam(value = "DNI del alumno que actualizaremos.", required = true)
			@PathVariable String id,
			@ApiParam(value = "Alumno que utilizaremos para actualizar el que existe en la BBDD.", required = true)
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
