package br.com.ezpet.nimbus21.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ezpet.nimbus21.domain.Marca;
import br.com.ezpet.nimbus21.domain.Produto;
import lombok.Getter;

@Getter
public class MarcaDTO {

	private Long codigo;
	private String nome;
	private String logo;
	private Boolean ativo;
	private List<Produto> produtos;
	
	public MarcaDTO(Marca marca) {
		this.codigo = marca.getCodigo();
		this.nome = marca.getNome();
		this.logo = marca.getLogo();
		this.ativo = marca.getAtivo();
		this.produtos = marca.getProdutos();
	}
	
	public static List<MarcaDTO> converter(List<Marca> marcas) {
		return marcas.stream().map(MarcaDTO::new).collect(Collectors.toList());
	}
}
