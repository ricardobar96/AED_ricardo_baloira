package es.iespuertodelacruz.ricardo.matriculasREST.controller.v2;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value= "MatriculaRESTv3", description = "REST APIs relacionadas con la entidad Asignatura accesibles para un usuario correctamente autenticado")
@RestController
@RequestMapping("/api/v2/asignaturas")
public class AsignaturaRESTv2 {
	private Logger logger = LoggerFactory.getLogger(AsignaturaRESTv2.class);
	@Autowired
	AsignaturasService asignaturasService;
	@GetMapping("")
	@ApiOperation(value = "Devuelve lista de todas las asignaturas", tags = "getAll")
	public List<Asignatura> getAll(){
		ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
		asignaturasService
		.findAll()
		.forEach(a -> asignaturas.add((Asignatura) a) );
		return asignaturas;
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Busca una asignatura por su Id", tags = "get")
	@ApiImplicitParam(name = "id", value = "Id de la asignatura", required = true, dataType = "Integer", paramType = "query")
	public ResponseEntity<?> getAsignaturaById(@PathVariable Integer id){
		Optional<Asignatura> asignaturaOPT = asignaturasService.findById(id);
		if (asignaturaOPT.isPresent()) {
			return ResponseEntity.ok(asignaturaOPT);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La asignatura no existe");
		}
		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Borra una asignatura por su Id", tags = "delete")
	@ApiImplicitParam(name = "id", value = "Id de la asignatura", required = true, dataType = "Integer", paramType = "query")
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
	@ApiOperation(value = "Crea una asignatura", tags = "post")
	public ResponseEntity<?> save(@RequestBody AsignaturaDTO asignaturaDto){
		Asignatura a = new Asignatura();
		a.setCurso(asignaturaDto.getCurso());
		a.setNombre(asignaturaDto.getNombre());

		asignaturasService.save(a);
		return ResponseEntity.ok().body(new AsignaturaDTO(a));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Modifica una asignatura", tags = "put")
	@ApiImplicitParam(name = "id", value = "Id de la asignatura", required = true, dataType = "Integer", paramType = "query")
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
