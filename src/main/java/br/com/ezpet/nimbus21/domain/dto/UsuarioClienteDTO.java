package br.com.ezpet.nimbus21.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ezpet.nimbus21.domain.Mascote;
import br.com.ezpet.nimbus21.domain.UsuarioCliente;
import br.com.ezpet.nimbus21.domain.tipos.TipoUsuario;
import lombok.Getter;

@Getter
public class UsuarioClienteDTO {

	private Long codigo;
	private TipoUsuario tipoUsuario;
	private String nome;
	private String email;
	private String senha;
	private List<Mascote> mascotes;
	
	public UsuarioClienteDTO(UsuarioCliente usuarioCliente) {
		this.codigo = usuarioCliente.getCodigo();
		this.tipoUsuario = usuarioCliente.getTipoUsuario();
		this.nome = usuarioCliente.getNome();
		this.email = usuarioCliente.getEmail();
		this.senha = usuarioCliente.getSenha();
		this.mascotes = usuarioCliente.getMascotes();
	}
	
	public static List<UsuarioClienteDTO> converter (List<UsuarioCliente> usuarioClientes) {
		return usuarioClientes.stream().map(UsuarioClienteDTO::new).collect(Collectors.toList());
	}

}
