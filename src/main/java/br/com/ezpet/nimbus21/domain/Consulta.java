package br.com.ezpet.nimbus21.domain;

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
@Table(name =  "TB_CONSULTA")
@SequenceGenerator(name = "consultaSequence", sequenceName = "SQ_CONSULTA", allocationSize = 1)
public class Consulta {
	
	@Id
	@Column(name = "cd_consulta")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consultaSequence")
	private Long codigo;
	
	@Column(name = "ds_consulta")
	private String detalhe;
	
	@ManyToOne
	@JoinColumn(name = "cd_mascote", nullable = false)
	@JsonBackReference(value="mascote-consulta")
	private Mascote mascote;

}
