package br.com.ezpet.nimbus21.domain.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.ezpet.nimbus21.domain.Produto;
import br.com.ezpet.nimbus21.domain.Role;
import br.com.ezpet.nimbus21.domain.UsuarioComercial;
import br.com.ezpet.nimbus21.domain.tipos.TipoUsuario;
import lombok.Getter;

@Getter
public class UsuarioComercialDTO {

	private Long codigo;
	private String nome;
	private TipoUsuario tipoUsuario;
	private String email;
	private String senha;
	private List<Produto> produtos;
	private Collection<Role> roles;
	
	public UsuarioComercialDTO(UsuarioComercial usuarioComercial) {
		this.codigo = usuarioComercial.getCodigo();
		this.nome = usuarioComercial.getNome();
		this.tipoUsuario = usuarioComercial.getTipoUsuario();
		this.produtos = usuarioComercial.getProdutos();
		this.email = usuarioComercial.getEmail();
		this.senha = usuarioComercial.getSenha();
		this.roles = usuarioComercial.getRoles();
	}
	
	public static List<UsuarioComercialDTO> converter(List<UsuarioComercial> usuariosComercial) {
		return usuariosComercial.stream().map(UsuarioComercialDTO::new).collect(Collectors.toList());
	}
	
}
