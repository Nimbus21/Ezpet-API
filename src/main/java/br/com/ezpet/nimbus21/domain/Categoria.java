package br.com.ezpet.nimbus21.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import br.com.ezpet.nimbus21.domain.tipos.TipoFisico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "TB_CATEGORIA")
@SequenceGenerator(name = "categoriaSequence", sequenceName = "SQ_CATEGORIA", allocationSize = 1)
public class Categoria {

	@Id
	@Column(name = "cd_categoria")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoriaSequence")
	private Long codigo;
	
	@Column(name = "nm_categoria")
	private String nome;
	
	@Column(name = "ds_icone")
	private String icone;
	
	@Column(name = "st_ativo")
	private Boolean ativo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ds_tipo_fisico")
	private TipoFisico tipoFisico;
	
	@ManyToOne(cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "cd_especie", nullable = false)
	@JsonBackReference(value="especie-categoria")
	private Especie especie;
	
	@JsonManagedReference(value="categoria-subcategoria")
	@OneToMany(mappedBy="categoria", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Subcategoria> subcategorias = new ArrayList<Subcategoria>();

}
