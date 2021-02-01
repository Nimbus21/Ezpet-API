package br.com.ezpet.nimbus21.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table (name = "TB_ROLE")
@SequenceGenerator(name = "roleSequence", sequenceName = "SQ_ROLE", allocationSize = 1)
public class Role {

	@Id
	@Column(name = "cd_role")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSequence")
	private Long codigo;
	
	@Column(name = "nm_role")
	private String nome;
	
	public Role(String nome) {
        this.nome = nome;
    }

	
}
