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
@Table (name = "TB_ESPECIE")
@SequenceGenerator(name = "especieSequence", sequenceName = "SQ_ESPECIE", allocationSize = 1)
public class Especie {

	@Id
	@Column(name = "cd_especie")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "especieSequence")
	private Long codigo;
	
	@Column(name = "nm_especie")
	private String nome;
	
	@Column(name = "ds_especie")
	private String descricao;
	
	@Column(name = "ds_icone")
	private String icone;
	
	@Column(name = "st_ativo")
	private Boolean ativo;
	
	@JsonManagedReference(value="especie-categoria")
	@OneToMany(mappedBy="especie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
}
