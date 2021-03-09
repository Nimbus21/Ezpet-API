package br.com.ezpet.nimbus21.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ezpet.nimbus21.domain.Categoria;
import br.com.ezpet.nimbus21.domain.Especie;
import br.com.ezpet.nimbus21.domain.Foto;
import lombok.Getter;

@Getter
public class EspecieDTO {

	private Long codigo;
	private String nome;
	private String descricao;
	private String icone;
	private Boolean ativo;
	private List<Categoria> categorias;
	
	public EspecieDTO(Especie especie) {
		this.codigo = especie.getCodigo();
		this.nome = especie.getNome();
		this.descricao = especie.getDescricao();
		this.icone = especie.getIcone();
		this.ativo = especie.getAtivo();
		this.categorias = especie.getCategorias();
	}
	
	public static List<EspecieDTO> converter(List<Especie> especies) {
		return especies.stream().map(EspecieDTO::new).collect(Collectors.toList());
	}
}
