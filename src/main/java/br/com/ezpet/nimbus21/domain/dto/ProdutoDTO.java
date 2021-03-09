package br.com.ezpet.nimbus21.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ezpet.nimbus21.domain.Produto;
import br.com.ezpet.nimbus21.domain.Subcategoria;
import br.com.ezpet.nimbus21.domain.UsuarioComercial;
import br.com.ezpet.nimbus21.domain.tipos.TipoAnimal;
import br.com.ezpet.nimbus21.domain.tipos.TipoFisico;
import br.com.ezpet.nimbus21.domain.tipos.TipoProduto;
import lombok.Getter;

@Getter
public class ProdutoDTO {

	private Long codigo;
	private String foto;
	private String nome;
	private String descricao;
	private String especificacao;
	private Double preco;
	private Double precoAntigo;
	private TipoProduto tipoProduto;
	private Subcategoria subcategoria;
	private UsuarioComercial usuarioComercial;

	public ProdutoDTO(Produto produto) {
		this.codigo = produto.getCodigo();
		this.foto = produto.getFoto();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.especificacao = produto.getEspecificacao();
		this.preco = produto.getPreco();
		this.precoAntigo = produto.getPrecoAntigo();
		this.tipoProduto = produto.getTipoProduto();
		this.subcategoria = produto.getSubcategoria();
		this.usuarioComercial = produto.getUsuarioComercial();
	}
	
	public static List<ProdutoDTO> converter(List<Produto> produtos) {
		return produtos.stream().map(ProdutoDTO::new).collect(Collectors.toList());
	}
	
}
