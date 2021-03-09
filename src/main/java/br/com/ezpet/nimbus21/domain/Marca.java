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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	@JsonManagedReference(value="marca-produto")
	@OneToMany(mappedBy="marca", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Produto> produtos = new ArrayList<Produto>();

}
