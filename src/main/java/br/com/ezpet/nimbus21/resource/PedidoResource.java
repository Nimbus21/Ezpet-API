package br.com.ezpet.nimbus21.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ezpet.nimbus21.domain.Pedido;
import br.com.ezpet.nimbus21.domain.dto.PedidoDTO;
import br.com.ezpet.nimbus21.repository.PedidoRepository;

@RestController
@RequestMapping("pedido")
public class PedidoResource {
	@Autowired
	PedidoRepository pedidoRepo;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<PedidoDTO> readAll() {
		List<Pedido> pedidos = pedidoRepo.findAll();
		return PedidoDTO.converter(pedidos);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PedidoDTO> read(@PathVariable("id") Long codigo) {
		Optional<Pedido> pedido = pedidoRepo.findById(codigo);
		return pedido.map(p -> ResponseEntity.ok(new PedidoDTO(p))).orElse(ResponseEntity.notFound().build());
	}
}
