package br.com.ezpet.nimbus21.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ezpet.nimbus21.domain.Mascote;
import br.com.ezpet.nimbus21.domain.UsuarioCliente;
import lombok.Getter;

@Getter
public class MascoteDTO {

	private Long codigo;
	private String nome;
	private UsuarioCliente usuarioCliente;

	public MascoteDTO(Mascote mascote) {
		this.codigo = mascote.getCodigo();
		this.nome = mascote.getNome();
		this.usuarioCliente = mascote.getUsuarioCliente();
	}
	
	public static List<MascoteDTO> converter(List<Mascote> mascotes) {
		return mascotes.stream().map(MascoteDTO::new).collect(Collectors.toList());
	}

}
