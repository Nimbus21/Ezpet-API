package br.com.ezpet.nimbus21.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ezpet.nimbus21.domain.UsuarioAdmin;
import br.com.ezpet.nimbus21.domain.tipos.Role;
import lombok.Getter;

@Getter
public class UsuarioAdminDTO {

	private Long codigo;
	private String nome;
	private String email;
	private String senha;
	private Role role = Role.ROLE_ADMIN;

	public UsuarioAdminDTO(UsuarioAdmin usuarioAdmin) {
		this.codigo = usuarioAdmin.getCodigo();
		this.nome = usuarioAdmin.getNome();
		this.email = usuarioAdmin.getEmail();
		this.senha = usuarioAdmin.getSenha();
		this.role = usuarioAdmin.getRole();
	}
	
	public static List<UsuarioAdminDTO> converter (List<UsuarioAdmin> usuariosAdmin) {
		return usuariosAdmin.stream().map(UsuarioAdminDTO::new).collect(Collectors.toList());
	}
	
	
}
