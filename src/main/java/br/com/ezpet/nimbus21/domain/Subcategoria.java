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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "TB_SUBCATEGORIA")
@SequenceGenerator(name = "subcategoriaSequence", sequenceName = "SQ_SUBCATEGORIA", allocationSize = 1)
public class Subcategoria {

	@Id
	@Column(name = "cd_subcategoria")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subcategoriaSequence")
	private Long codigo;
	
	@Column(name = "nm_subcategoria")
	private String nome;
	
	@Column(name = "ds_subcategoria")
	private String descricao;
	
	@Column(name = "ds_icone")
	private String icone;
	
	@Column(name = "st_ativo")
	private Boolean ativo;
	
	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cd_categoria", nullable = false)
	@JsonBackReference(value="categoria-subcategoria")
	private Categoria categoria;
	
	@JsonManagedReference(value="subcategoria-produto")
	@OneToMany(mappedBy="subcategoria", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Produto> produtos = new ArrayList<Produto>();

}
