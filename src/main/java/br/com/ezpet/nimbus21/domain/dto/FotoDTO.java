package br.com.ezpet.nimbus21.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ezpet.nimbus21.domain.Foto;
import br.com.ezpet.nimbus21.domain.Mascote;
import br.com.ezpet.nimbus21.domain.UsuarioCliente;
import lombok.Getter;

@Getter
public class FotoDTO {

	private Long codigo;
	private String nome;
	private String link;
	private byte[] fotoByte;
	private UsuarioCliente usuarioCliente;
	private Mascote mascote;
	
	public FotoDTO(Foto foto) {
		this.codigo = foto.getCodigo();
		this.nome = foto.getNome();
		this.link = foto.getLink();
		this.fotoByte = foto.getFotoByte();
		this.usuarioCliente = foto.getUsuarioCliente();
		this.mascote = foto.getMascote();
	}
	
	public static List<FotoDTO> converter(List<Foto> fotos) {
		return fotos.stream().map(FotoDTO::new).collect(Collectors.toList());
	}
}
