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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_USUARIO_CLIENTE")
@SequenceGenerator(name = "usuarioSequence", sequenceName = "SQ_USUARIO", allocationSize = 1)
public class UsuarioCliente {

	@Id
	@Column(name = "cd_usuario_cliente")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioSequence")
	private Long codigo;

	@Column(name = "nm_usuario_cliente")
	private String nome;
	
	@Column(name = "vl_email_cliente")
	private String email;
	
	@Column(name = "tx_senha_cliente")
	private String senha;
	
	@Column(name = "ds_localizacao_cliente")
	private String localizacao;
	
//	@Enumerated(EnumType.STRING)
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ds_tipo_usuario")
	private TipoUsuario tipoUsuario = TipoUsuario.MASCOTE;
	
	@JsonManagedReference(value="cliente-mascote")
	@OneToMany(mappedBy="usuarioCliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Mascote> mascotes = new ArrayList<Mascote>();
	
	@JsonManagedReference(value="cliente-post")
	@OneToMany(mappedBy="usuarioCliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Post> posts = new ArrayList<Post>();
	
	@JsonManagedReference(value="cliente-reco")
	@OneToMany(mappedBy="usuarioCliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Recomendacao> recomendacoes = new ArrayList<Recomendacao>();
	
	@JsonManagedReference(value="cliente-ender")
	@OneToMany(mappedBy="usuarioCliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
	@JsonManagedReference(value="cliente-pedido")
	@OneToMany(mappedBy="usuarioCliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	
	@JsonManagedReference(value="cliente-foto")
	@OneToMany(mappedBy="usuarioCliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Foto> fotos = new ArrayList<Foto>();
	
}
