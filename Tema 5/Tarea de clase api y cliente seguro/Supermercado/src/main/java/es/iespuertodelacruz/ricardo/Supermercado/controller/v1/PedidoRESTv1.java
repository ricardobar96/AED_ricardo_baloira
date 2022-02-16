package es.iespuertodelacruz.ricardo.Supermercado.controller.v1;

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

import es.iespuertodelacruz.ricardo.Supermercado.entities.Pedido;
import es.iespuertodelacruz.ricardo.Supermercado.entities.Producto;
import es.iespuertodelacruz.ricardo.Supermercado.services.PedidoService;
import es.iespuertodelacruz.ricardo.Supermercado.services.ProductoService;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoRESTv1 {
	private Logger logger = LoggerFactory.getLogger(PedidoRESTv1.class);
	@Autowired
	PedidoService pedidosService;
	@GetMapping("")
	public List<Pedido> getAll(){
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		pedidosService
		.findAll()
		.forEach(p -> pedidos.add((Pedido) p) );
		return pedidos;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPedidoById(@PathVariable Integer id){
		Optional<Pedido> pedidoOPT = pedidosService.findById(id);
		if (pedidoOPT.isPresent()) {
			return ResponseEntity.ok(pedidoOPT);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El pedido no existe");
		}
		
	}		
}
