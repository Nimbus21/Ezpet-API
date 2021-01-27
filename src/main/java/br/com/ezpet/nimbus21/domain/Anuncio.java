package br.com.ezpet.nimbus21.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name =  "TB_ANUNCIO")
@SequenceGenerator(name = "anuncioSequence", sequenceName = "SQ_ANUNCIO", allocationSize = 1)
public class Anuncio {
	
	@Id
	@Column(name = "cd_anuncio")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "anuncioSequence")
	private Long codigo;
	
	@Column(name = "fl_foto")
	private String foto;
}
