package br.com.ezpet.nimbus21.domain;

import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "TB_USUARIO_COMERCIAL")
@SequenceGenerator(name = "usuarioComercialSequence", sequenceName = "SQ_USUARIO_COMERCIAL", allocationSize = 1)
public class UsuarioComercial {

	@Id
	@Column(name = "cd_usuario_comercial")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioComercialSequence")
	private Long codigo;
	
	@Column(name = "nm_usuario_comercial")
	private String nome;
	
	@Column(name = "nr_cnpj")
	private String cnpj;
	
	@Column(name = "tx_senha")
	private String senha;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ds_tipo_usuario")
	private TipoUsuario tipoUsuario;
	
	@Column(name = "ds_horario_funcionamento")
	private String horarioFuncionamento;
	
	@Column(name = "fl_foto")
	private String foto;
	
	@Column(name = "ds_cep")
	private String cep;
	
	@Column(name = "nm_contato")
	private String nomeContato;
	
	@Column(name = "nr_telefone")
	private String telefone;
	
	@Column(name = "ds_email")
	private String email;
	
	@JsonManagedReference(value="comercial-produto")
	@OneToMany(mappedBy="usuarioComercial", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@JsonManagedReference(value="comercial-post")
	@OneToMany(mappedBy="usuarioComercial", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Post> posts = new ArrayList<Post>();
	
	@OneToOne
	@JoinColumn(name = "cd_endereco")
	private Endereco endereco;
	
	public void addProduto(Produto produto) {
		produto.setUsuarioComercial(this);
		produtos.add(produto);
	}

}