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

import br.com.ezpet.nimbus21.domain.tipos.TipoProduto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
	
	@Column(name = "ds_preco_antigo")
	private Double precoAntigo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ds_tipo_produto")
	private TipoProduto tipoProduto;
	
//	@Enumerated(EnumType.STRING)
//	@Column(name = "ds_tipo_animal")
//	private TipoAnimal tipoAnimal;
	
	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cd_subcategoria", nullable = false)
	@JsonBackReference(value = "subcategoria-produto")
	private Subcategoria subcategoria;
	
	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cd_marca", nullable = false)
	@JsonBackReference(value = "marca-produto")
	private Marca marca;
	
	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cd_usuario_comercial", nullable = false)
	@JsonBackReference(value="comercial-produto")
	private UsuarioComercial usuarioComercial;
	
	//acho que iremos tirar a bidirecionalidade
	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cd_pedido", nullable = true)
	@JsonBackReference(value="pedido-produto")
	private Pedido pedido;

}
