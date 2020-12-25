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

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.ezpet.nimbus21.domain.tipos.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_USUARIO_COMERCIAL")
@SequenceGenerator(name = "usuarioComercialSequence", sequenceName = "SQ_USUARIO_COMERCIAL", allocationSize = 1)
public class UsuarioComercial {

	@Id
	@Column(name = "cd_usuario_comercial")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioComercialSequence")
	private Long codigo;
	
	@Column(name = "nm_usuario_comercial")
	private String nome; //Petshop Pedrao
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ds_tipo_usuario")
	private TipoUsuario tipoUsuario; //loja
	
	@JsonManagedReference
	@OneToMany(mappedBy="usuarioComercial", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Produto> produtos = new ArrayList<Produto>(); //Bolinha, boneco, coleira
	
	public UsuarioComercial() {
		
	}
	
	public UsuarioComercial(Long codigo, String nome, TipoUsuario tipoUsuario) {
		this.codigo = codigo;
		this.nome = nome;
		this.tipoUsuario = tipoUsuario;
	}
	
	public void addProduto(Produto produto) {
		produto.setUsuarioComercial(this);
		produtos.add(produto);
	}

}
