package br.com.ezpet.nimbus21.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ezpet.nimbus21.domain.Categoria;
import br.com.ezpet.nimbus21.domain.Mascote;
import br.com.ezpet.nimbus21.domain.Produto;
import br.com.ezpet.nimbus21.domain.Subcategoria;
import lombok.Getter;

@Getter
public class SubcategoriaDTO {

	private Long codigo;
	private String nome;
	private String descricao;
	private String icone;
	private Boolean ativo;
	private Categoria categoria;
	private List<Produto> produtos;
	
	public SubcategoriaDTO(Subcategoria subcategoria) {
		this.codigo = subcategoria.getCodigo();
		this.nome = subcategoria.getNome();
		this.descricao = subcategoria.getDescricao();
		this.icone = subcategoria.getIcone();
		this.ativo = subcategoria.getAtivo();
		this.categoria = subcategoria.getCategoria();
		this.produtos = subcategoria.getProdutos();
	}
	
	public static List<SubcategoriaDTO> converter(List<Subcategoria> subcategorias) {
		return subcategorias.stream().map(SubcategoriaDTO::new).collect(Collectors.toList());
	}
}
