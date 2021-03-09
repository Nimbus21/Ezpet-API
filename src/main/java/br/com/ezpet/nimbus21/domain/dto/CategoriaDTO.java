package br.com.ezpet.nimbus21.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ezpet.nimbus21.domain.Categoria;
import br.com.ezpet.nimbus21.domain.Especie;
import br.com.ezpet.nimbus21.domain.Subcategoria;
import lombok.Getter;

@Getter
public class CategoriaDTO {

	private Long codigo;
	private String nome;
	private String icone;
	private Boolean ativo;
	private Especie especie;
	private List<Subcategoria> subcategorias;
	
	public CategoriaDTO(Categoria categoria) {
		this.codigo = categoria.getCodigo();
		this.nome = categoria.getNome();
		this.icone = categoria.getIcone();
		this.ativo = categoria.getAtivo();
		this.especie = categoria.getEspecie();
		this.subcategorias = categoria.getSubcategorias();
	}
	
	public static List<CategoriaDTO> converter(List<Categoria> categorias) {
		return categorias.stream().map(CategoriaDTO::new).collect(Collectors.toList());
	}
}
