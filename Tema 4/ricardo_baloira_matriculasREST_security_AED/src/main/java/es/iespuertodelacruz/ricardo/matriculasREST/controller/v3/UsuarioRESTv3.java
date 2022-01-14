package es.iespuertodelacruz.ricardo.matriculasREST.controller.v3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.ricardo.matriculasREST.dto.UsuarioDTO;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Usuarioconrol;
import es.iespuertodelacruz.ricardo.matriculasREST.services.UsuarioService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v3/usuarios")
public class UsuarioRESTv3 {
	
	private Logger logger = LoggerFactory.getLogger(UsuarioRESTv3.class);
	@Autowired
	UsuarioService usuarioService;
	@GetMapping("")
	@ApiOperation(value = "Devuelve lista de todos los usuarios", tags = "getAll")
	public List<Usuarioconrol> getAll(){
		ArrayList<Usuarioconrol> usuarioconrol = new ArrayList<Usuarioconrol>();
		usuarioService
		.findAll()
		.forEach(a -> usuarioconrol.add((Usuarioconrol) a) );
		return usuarioconrol;
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Busca un usuario por su Id", tags = "get")
	@ApiImplicitParam(name = "id", value = "Id del usuario", required = true, dataType = "Integer", paramType = "query")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		Optional<Usuarioconrol> usuarioOPT = usuarioService.findById(id);
		if (usuarioOPT.isPresent()) {
			return ResponseEntity.ok(usuarioOPT);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
		}
		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Borra un usuario por su Id", tags = "delete")
	@ApiImplicitParam(name = "id", value = "Id del usuario", required = true, dataType = "Integer", paramType = "query")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Optional<Usuarioconrol> usuarioOPT = usuarioService.findById(id);
		
		if(usuarioOPT.isPresent()) {
			usuarioService.deleteById(id);
			return ResponseEntity.ok("Usuario eliminado");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no existe");
		}	
	}
	
	@PostMapping
	@ApiOperation(value = "Crea un usuario", tags = "post")
	public ResponseEntity<?> save(@RequestBody UsuarioDTO usuarioDto){
		String enhash = BCrypt.hashpw(usuarioDto.getPassword(), BCrypt.gensalt(10));
		Usuarioconrol u = new Usuarioconrol();
		u.setNombre(usuarioDto.getNombre());
		u.setPassword(enhash);
		if(!usuarioDto.getRol().equals("ROLE_ADMIN") && !usuarioDto.getRol().equals("ROLE_USER")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El rol debe ser 'ROLE_USER' o 'ROLE_ADMIN'");
		}
		u.setRol(usuarioDto.getRol());

		usuarioService.save(u);
		return ResponseEntity.ok().body(new UsuarioDTO(u));
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Modifica un usuario", tags = "put")
	@ApiImplicitParam(name = "id", value = "Id del usuario", required = true, dataType = "Integer", paramType = "query")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDto){
		Optional<Usuarioconrol> usuarioOPT = usuarioService.findById(id);
		if(usuarioOPT.isPresent()) {
			String enhash = BCrypt.hashpw(usuarioDto.getPassword(), BCrypt.gensalt(10));
			Usuarioconrol u = usuarioOPT.get();

			if(usuarioDto.getNombre()!=null) {
				u.setNombre(usuarioDto.getNombre());
			}
			if(usuarioDto.getPassword()!=null) {
				u.setPassword(enhash);
			}	
			if(usuarioDto.getRol()!=null) {
				if(!usuarioDto.getRol().equals("ROLE_ADMIN") && !usuarioDto.getRol().equals("ROLE_USER")) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El rol debe ser 'ROLE_USER' o 'ROLE_ADMIN'");
				}
				u.setRol(usuarioDto.getRol());
			}	
			
			return ResponseEntity.ok(usuarioService.save(u));
			
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no existe");
		}
	}
}

