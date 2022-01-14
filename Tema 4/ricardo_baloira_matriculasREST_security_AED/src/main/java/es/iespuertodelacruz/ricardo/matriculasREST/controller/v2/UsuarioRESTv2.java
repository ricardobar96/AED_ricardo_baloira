package es.iespuertodelacruz.ricardo.matriculasREST.controller.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.ricardo.matriculasREST.controller.v3.UsuarioRESTv3;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Usuarioconrol;
import es.iespuertodelacruz.ricardo.matriculasREST.services.UsuarioService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v2/usuarios")
public class UsuarioRESTv2 {
	
	private Logger logger = LoggerFactory.getLogger(UsuarioRESTv2.class);
	@Autowired
	UsuarioService usuarioService;
	@GetMapping("")
	@ApiOperation(value = "Devuelve lista de los nombres de todos los usuarios", tags = "getAll")
	public List<String> getAll(){
		ArrayList<String> usuarioconrol = new ArrayList<String>();
		usuarioService
		.findAll()
		.forEach(a -> usuarioconrol.add(a.getNombre()));
		return usuarioconrol;
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Busca el nombre de un usuario por su Id", tags = "get")
	@ApiImplicitParam(name = "id", value = "Id del usuario", required = true, dataType = "Integer", paramType = "query")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		Optional<Usuarioconrol> usuarioOPT = usuarioService.findById(id);
		if (usuarioOPT.isPresent()) {
			return ResponseEntity.ok(usuarioOPT.get().getNombre());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
		}
		
	}
	
}