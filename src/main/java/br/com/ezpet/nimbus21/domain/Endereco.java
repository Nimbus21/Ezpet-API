package br.com.ezpet.nimbus21.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "TB_ENDERECO")
@SequenceGenerator(name = "enderecoSequence", sequenceName = "SQ_ENDERECO", allocationSize = 1)
public class Endereco {
	
	@Id
	@Column(name = "cd_endereco")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enderecoSequence")
	private Long codigo;
	
	@Column(name = "ds_cidade")
	private String cidade;
	
	@Column(name = "ds_estado")
	private String estado;
	
	@Column(name = "ds_cep")
	private Integer cep;
	
	@ManyToOne
	@JoinColumn(name = "cd_usuario_cliente", nullable = true)
	@JsonBackReference(value="cliente-ender")
	private UsuarioCliente usuarioCliente;
	
	@OneToOne(mappedBy="endereco")
	private UsuarioComercial usuarioComercial;
	
}
