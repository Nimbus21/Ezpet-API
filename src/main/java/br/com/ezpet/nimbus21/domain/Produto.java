package br.com.ezpet.nimbus21.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.ezpet.nimbus21.domain.tipos.TipoAnimal;
import br.com.ezpet.nimbus21.domain.tipos.TipoFisico;
import br.com.ezpet.nimbus21.domain.tipos.TipoProduto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_PRODUTO")
@SequenceGenerator(name = "produtoSequence", sequenceName = "SQ_PRODUTO", allocationSize = 1)
public class Produto {

	@Id
	@Column(name = "cd_produto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produtoSequence")
	private Long codigo;
	
	@Column(name = "ft_produto")
	private String foto;
	
	@Column(name = "nm_produto")
	private String nome;
	
	@Column(name = "ds_produto")
	private String descricao;
	
	@Column(name = "ds_preco")
	private Double preco;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ds_tipo_produto")
	private TipoProduto tipoProduto;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ds_tipo_animal")
	private TipoAnimal tipoAnimal;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ds_tipo_fisico")
	private TipoFisico tipoFisico;
	
	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cd_usuario_comercial", nullable = false)
	@JsonBackReference
	private UsuarioComercial usuarioComercial;
	
	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cd_pedido", nullable = true)
	@JsonBackReference
	private Pedido pedido;
	
	
	public Produto() {
		
	}

	public Produto(Long codigo, String foto, String nome, String descricao, Double preco, TipoProduto tipoProduto, TipoAnimal tipoAnimal, TipoFisico tipoFisico, UsuarioComercial usuarioComercial) {
		this.codigo = codigo;
		this.foto = foto;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.tipoProduto = tipoProduto;
		this.tipoAnimal = tipoAnimal;
		this.tipoFisico = tipoFisico;
		this.usuarioComercial = usuarioComercial;
	}
	
}
