package br.com.ezpet.nimbus21.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ezpet.nimbus21.domain.UsuarioColaborador;
import br.com.ezpet.nimbus21.domain.tipos.Role;
import lombok.Getter;

@Getter
public class UsuarioColaboradorDTO {

	private Long codigo;
	private String email;
	private String senha;
	private Role role;
	
	public UsuarioColaboradorDTO(UsuarioColaborador usuarioColaborador) {
		this.codigo = usuarioColaborador.getCodigo();
		this.email = usuarioColaborador.getEmail();
		this.senha = usuarioColaborador.getSenha();
		this.role = usuarioColaborador.getRole();
	}
	
	public static List<UsuarioColaboradorDTO> converter (List<UsuarioColaborador> usuariosColaborador) {
		return usuariosColaborador.stream().map(UsuarioColaboradorDTO::new).collect(Collectors.toList());
	}
	
	
}
