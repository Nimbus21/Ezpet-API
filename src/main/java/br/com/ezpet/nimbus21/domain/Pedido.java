package br.com.ezpet.nimbus21.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pedido {

	private Long codigo;
	
	@JsonManagedReference
	@OneToMany(mappedBy="pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cd_usuario_cliente", nullable = false)
	@JsonBackReference
	private UsuarioCliente usuarioCliente;
}
