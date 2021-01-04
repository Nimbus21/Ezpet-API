package br.com.ezpet.nimbus21.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ezpet.nimbus21.domain.Pedido;
import lombok.Getter;

@Getter
public class PedidoDTO {

	private Long codigo;
	
	public PedidoDTO(Pedido pedido) {
		this.codigo = pedido.getCodigo();
	}
	
	public static List<PedidoDTO> converter(List<Pedido> pedidos) {
		return pedidos.stream().map(PedidoDTO::new).collect(Collectors.toList());
	}

}
