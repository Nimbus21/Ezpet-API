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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
	
	@Column(name = "ft_mascote")
	private String foto;
	
	@ManyToOne
	@JoinColumn(name = "cd_usuario_cliente", nullable = false)
	@JsonBackReference(value="cliente-mascote")
	private UsuarioCliente usuarioCliente;
	
	@JsonManagedReference(value="mascote-post")
	@OneToMany(mappedBy="mascote", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Post> posts = new ArrayList<Post>();
	
	@JsonManagedReference(value="mascote-amigo")
	@OneToMany(mappedBy="mascote", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<AmigoMascote> amigosMascote = new ArrayList<AmigoMascote>();
	
	@JsonManagedReference(value="mascote-foto")
	@OneToMany(mappedBy="mascote", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Foto> fotos = new ArrayList<Foto>();
	
	@JsonManagedReference(value="mascote-consulta")
	@OneToMany(mappedBy="mascote", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Consulta> consultas = new ArrayList<Consulta>();

}
