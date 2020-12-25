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
@Table (name = "TB_MASCOTE")
@SequenceGenerator(name = "mascoteSequence", sequenceName = "SQ_MASCOTE", allocationSize = 1)
public class Mascote {

	@Id
	@Column(name = "cd_mascote")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mascoteSequence")
	private Long codigo;

	@Column(name = "nm_mascote")
	private String nome;
	
	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cd_usuario_cliente", nullable = false)
	@JsonBackReference
	private UsuarioCliente usuarioCliente;
	
	@JsonManagedReference
	@OneToMany(mappedBy="mascote", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<AmigoMascote> amigosMascote = new ArrayList<AmigoMascote>();

	public Mascote() {

	}
	
	public Mascote(Long codigo, String nome, UsuarioCliente usuarioCliente) {
		this.codigo = codigo;
		this.nome = nome;
		this.usuarioCliente = usuarioCliente;
	}

}
