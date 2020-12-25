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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.ezpet.nimbus21.domain.tipos.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_USUARIO_CLIENTE")
@SequenceGenerator(name = "usuarioSequence", sequenceName = "SQ_USUARIO", allocationSize = 1)
public class UsuarioCliente {

	@Id
	@Column(name = "cd_usuario_cliente")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioSequence")
	private Long codigo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ds_tipo")
	private TipoUsuario tipoUsuario = TipoUsuario.MASCOTE;

	@Column(name = "nm_usuario_cliente")
	private String nome;
	
	@Column(name = "vl_email_cliente")
	private String email;
	
	@Column(name = "tx_senha_cliente")
	private String senha;
	
	@JsonManagedReference
	@OneToMany(mappedBy="usuarioCliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Mascote> mascotes = new ArrayList<Mascote>();
	
	@JsonManagedReference
	@OneToMany(mappedBy="usuarioCliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Pedido> pedidos = new ArrayList<Pedido>();

	public UsuarioCliente() {

	}

	public UsuarioCliente(Long codigo, TipoUsuario tipoUsuario, String nome, String email, String senha) {
		this.codigo = codigo;
		this.tipoUsuario = tipoUsuario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
}
