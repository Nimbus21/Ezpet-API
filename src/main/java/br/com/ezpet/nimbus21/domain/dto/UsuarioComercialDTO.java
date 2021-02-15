package br.com.ezpet.nimbus21.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ezpet.nimbus21.domain.Produto;
import br.com.ezpet.nimbus21.domain.UsuarioComercial;
import br.com.ezpet.nimbus21.domain.tipos.Role;
import br.com.ezpet.nimbus21.domain.tipos.TipoUsuario;
import lombok.Getter;

@Getter
public class UsuarioComercialDTO {

	private Long codigo;
	private String nome;
	private TipoUsuario tipoUsuario;
	private String email;
	private String senha;
	private String abertura;
	private String fechamento;
	private Role role;
	private List<Produto> produtos;
	
	public UsuarioComercialDTO(UsuarioComercial usuarioComercial) {
		this.codigo = usuarioComercial.getCodigo();
		this.nome = usuarioComercial.getNome();
		this.tipoUsuario = usuarioComercial.getTipoUsuario();
		this.produtos = usuarioComercial.getProdutos();
		this.email = usuarioComercial.getEmail();
		this.senha = usuarioComercial.getSenha();
		this.abertura = usuarioComercial.getAbertura();
		this.fechamento = usuarioComercial.getFechamento();
		this.role = usuarioComercial.getRole();
	}
	
	public static List<UsuarioComercialDTO> converter(List<UsuarioComercial> usuariosComercial) {
		return usuariosComercial.stream().map(UsuarioComercialDTO::new).collect(Collectors.toList());
	}
	
}
