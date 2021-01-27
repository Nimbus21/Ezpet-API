package br.com.ezpet.nimbus21.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.ezpet.nimbus21.domain.tipos.TipoEntrega;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PEDIDO")
@SequenceGenerator(name = "pedidoSequence", sequenceName = "SQ_PEDIDO", allocationSize = 1)
public class Pedido {

	@Id
	@Column(name = "cd_pedido")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedidoSequence")
	private Long codigo;
	
	@JsonManagedReference(value="pedido-produto")
	@OneToMany(mappedBy="pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ds_tipo_entrega")
	private TipoEntrega tipoEntrega;
	
	@Column(name = "ds_pedido")
	private String observacao;
	
	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cd_usuario_cliente", nullable = false)
	@JsonBackReference(value="cliente-pedido")
	private UsuarioCliente usuarioCliente;
}
