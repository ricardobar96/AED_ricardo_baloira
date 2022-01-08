package es.iespuertodelacruz.daniel.matriculasrest.controller;

import java.util.ArrayList;
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
import es.iespuertodelacruz.daniel.matriculasrest.dto.AsignaturaDTO;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Alumno;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Asignatura;
import es.iespuertodelacruz.daniel.matriculasrest.service.AsignaturaService;
import es.iespuertodelacruz.daniel.matriculasrest.service.MatriculaService;

@RestController
@RequestMapping("/api/asignatura")
public class AsignaturaREST {
	//private Logger logger = (Logger) LoggerFactory.logger(getClass());
	@Autowired
	AsignaturaService asignaturaService;
	@Autowired
	MatriculaService matriculaService;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<AsignaturaDTO> l = new ArrayList<>();
		for(Asignatura asignatura : asignaturaService.findAll()) {
			AsignaturaDTO asignaturaDto = new AsignaturaDTO(asignatura);
			l.add(asignaturaDto);
		}
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Optional<Asignatura> optM = asignaturaService.findById(id);
		if(optM.isPresent()) {
			asignaturaService.deleteById(id);
			return ResponseEntity.ok("asignatura borrada");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el id del registro no existe");
		}

	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getAlumnoById(@PathVariable("id") Integer id) {
		
		Optional<Asignatura> optAsignatura = asignaturaService.findById(id);
		if(optAsignatura.isPresent()) {
			return ResponseEntity.ok(optAsignatura.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id,
		@RequestBody Asignatura aDTO){
		Optional<Asignatura> optA = asignaturaService.findById(id);
		if(optA.isPresent()) {
			Asignatura a = optA.get();
			a.setNombre(aDTO.getNombre());
			a.setCurso(aDTO.getCurso());
			a.setMatriculas(aDTO.getMatriculas());
			return ResponseEntity.ok(asignaturaService.save(a));
		}else {
			return
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro no existe");
		}
	}
	@PostMapping
	public ResponseEntity<?> saveAsignatura(
			@RequestBody AsignaturaDTO asignaturaDto) {
		Asignatura asignatura = new Asignatura();
		asignatura.setCurso(asignaturaDto.getCurso());
		asignatura.setNombre(asignaturaDto.getNombre());
		Asignatura asignaturaC = null;
		try {
			asignaturaC = asignaturaService.save(asignatura);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (asignaturaC != null) {
			return new ResponseEntity<>(asignaturaC, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Ya existe esa combinación de valores (Curso, Nombre)", HttpStatus.CONFLICT);
		}
		
	}
}
