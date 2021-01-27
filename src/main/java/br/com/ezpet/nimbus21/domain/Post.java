package br.com.ezpet.nimbus21.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_POST")
@SequenceGenerator(name = "postSequence", sequenceName = "SQ_POST", allocationSize = 1)
public class Post {
	
	@Id
	@Column(name = "cd_post")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postSequence")
	private Long codigo;

	@Column(name = "ft_post", nullable = true)
	private String foto;
	
	@Column(name = "nr_likes")
	private Short likes;
	
	@Column(name = "tx_post")
	private String texto;
	
	@Column(name = "dt_post")
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "cd_usuario_cliente", nullable = true)
	@JsonBackReference(value="cliente-post")
	private UsuarioCliente usuarioCliente;
	
	@ManyToOne
	@JoinColumn(name = "cd_usuario_comercial", nullable = true)
	@JsonBackReference(value="comercial-post")
	private UsuarioComercial usuarioComercial;
	
	@ManyToOne
	@JoinColumn(name = "cd_mascote", nullable = true)
	@JsonBackReference(value="mascote-post")
	private Mascote mascote;
}
