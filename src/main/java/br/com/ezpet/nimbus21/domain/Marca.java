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
@Table (name = "TB_MARCA")
@SequenceGenerator(name = "marcaSequence", sequenceName = "SQ_MARCA", allocationSize = 1)
public class Marca {

	@Id
	@Column(name = "cd_marca")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marcaSequence")
	private Long codigo;
	
	@Column(name = "nm_marca")
	private String nome;
	
	@Column(name = "ds_logo")
	private String logo;
	
	@Column(name = "st_ativo")
	private Boolean ativo;

}
