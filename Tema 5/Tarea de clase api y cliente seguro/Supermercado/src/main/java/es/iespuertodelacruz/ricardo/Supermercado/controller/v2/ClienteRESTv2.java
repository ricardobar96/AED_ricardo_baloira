package es.iespuertodelacruz.ricardo.Supermercado.controller.v2;

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

import es.iespuertodelacruz.ricardo.Supermercado.entities.Cliente;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Pedido;
import es.iespuertodelacruz.ricardo.Supermercado.services.ClienteService;
import es.iespuertodelacruz.ricardo.Supermercado.services.PedidoService;

@RestController
@RequestMapping("/api/v2/clientes")
public class ClienteRESTv2 {
	private Logger logger = LoggerFactory.getLogger(ClienteRESTv2.class);
	@Autowired
	ClienteService clientesService;
	@GetMapping("")
	public List<Cliente> getAll(){
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientesService
		.findAll()
		.forEach(p -> clientes.add((Cliente) p) );
		return clientes;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getClienteById(@PathVariable Integer id){
		Optional<Cliente> clienteOPT = clientesService.findById(id);
		if (clienteOPT.isPresent()) {
			return ResponseEntity.ok(clienteOPT);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El cliente no existe");
		}
		
	}		
}
