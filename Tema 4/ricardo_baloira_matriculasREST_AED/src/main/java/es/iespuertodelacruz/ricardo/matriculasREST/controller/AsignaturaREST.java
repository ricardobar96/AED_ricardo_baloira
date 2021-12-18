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

import es.iespuertodelacruz.ricardo.matriculasREST.dto.AsignaturaDTO;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Asignatura;
import es.iespuertodelacruz.ricardo.matriculasREST.services.AsignaturasService;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaREST {
	private Logger logger = LoggerFactory.getLogger(AsignaturaREST.class);
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
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Optional<Asignatura> asignaturaOPT = asignaturasService.findById(id);
		
		if(asignaturaOPT.isPresent()) {
			asignaturasService.deleteById(id);
			return ResponseEntity.ok("Asignatura eliminada");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La asignatura no existe");
		}	
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody AsignaturaDTO asignaturaDto){
		Asignatura a = new Asignatura();
		a.setCurso(asignaturaDto.getCurso());
		a.setNombre(asignaturaDto.getNombre());

		asignaturasService.save(a);
		return ResponseEntity.ok().body(new AsignaturaDTO(a));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody AsignaturaDTO asignaturaDto){
		Optional<Asignatura> asignaturaOPT = asignaturasService.findById(id);
		if(asignaturaOPT.isPresent()) {
			Asignatura a = asignaturaOPT.get();

			if(asignaturaDto.getCurso()!=null) {
				a.setCurso(asignaturaDto.getCurso());
			}
			if(asignaturaDto.getNombre()!=null) {
				a.setNombre(asignaturaDto.getNombre());
			}	
			
			return ResponseEntity.ok(asignaturasService.save(a));
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La asignatura no existe");
		}
	}
}