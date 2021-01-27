package br.com.ezpet.nimbus21.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ezpet.nimbus21.domain.Endereco;
import br.com.ezpet.nimbus21.domain.Foto;
import br.com.ezpet.nimbus21.domain.Mascote;
import br.com.ezpet.nimbus21.domain.Pedido;
import br.com.ezpet.nimbus21.domain.Post;
import br.com.ezpet.nimbus21.domain.Recomendacao;
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
	private String localizacao;
	private List<Mascote> mascotes;
	private List<Post> posts;
	private List<Recomendacao> recomendacoes;
	private List<Endereco> enderecos;
	private List<Pedido> pedidos;
	private List<Foto> fotos;
	
	public UsuarioClienteDTO(UsuarioCliente usuarioCliente) {
		this.codigo = usuarioCliente.getCodigo();
		this.tipoUsuario = usuarioCliente.getTipoUsuario();
		this.nome = usuarioCliente.getNome();
		this.email = usuarioCliente.getEmail();
		this.senha = usuarioCliente.getSenha();
		this.localizacao = usuarioCliente.getLocalizacao();
		this.mascotes = usuarioCliente.getMascotes();
		this.posts = usuarioCliente.getPosts();
		this.recomendacoes = usuarioCliente.getRecomendacoes();
		this.enderecos = usuarioCliente.getEnderecos();
		this.pedidos = usuarioCliente.getPedidos();
		this.fotos = usuarioCliente.getFotos();
	}
	
	public static List<UsuarioClienteDTO> converter (List<UsuarioCliente> usuarioClientes) {
		return usuarioClientes.stream().map(UsuarioClienteDTO::new).collect(Collectors.toList());
	}

}
