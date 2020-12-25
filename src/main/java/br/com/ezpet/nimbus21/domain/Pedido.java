package br.com.ezpet.nimbus21.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_PEDIDO")
@SequenceGenerator(name = "pedidoSequence", sequenceName = "SQ_PEDIDO", allocationSize = 1)
public class Pedido {

	@Id
	@Column(name = "cd_pedido")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedidoSequence")
	private Long codigo;
	
	@JsonManagedReference
	@OneToMany(mappedBy="pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cd_usuario_cliente", nullable = false)
	@JsonBackReference
	private UsuarioCliente usuarioCliente;

	public Pedido() {
		
	}

	public Pedido(Long codigo, List<Produto> produtos, UsuarioCliente usuarioCliente) {
		this.codigo = codigo;
		this.produtos = produtos;
		this.usuarioCliente = usuarioCliente;
	}
	
}
