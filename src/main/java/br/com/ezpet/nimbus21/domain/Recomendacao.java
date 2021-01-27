package br.com.ezpet.nimbus21.domain;

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
@Table(name =  "TB_RECOMENDACAO")
@SequenceGenerator(name = "recomendacaoSequence", sequenceName = "SQ_RECOMENDACAO", allocationSize = 1)
public class Recomendacao {

	@Id
	@Column(name = "cd_recomendacao")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recomendacaoSequence")
	private Long codigo;
	
	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cd_usuario_cliente", nullable = false)
	@JsonBackReference(value="cliente-reco")
	private UsuarioCliente usuarioCliente;
	
	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cd_mascote", nullable = false)
//	@JsonBackReference(value="mascote-reco")
	private Mascote mascote;
	
}
