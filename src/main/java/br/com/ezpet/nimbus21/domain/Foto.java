package br.com.ezpet.nimbus21.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_FOTO")
@SequenceGenerator(name = "fotoSequence", sequenceName = "SQ_FOTO", allocationSize = 1)
public class Foto {

	@Id
	@Column(name = "cd_foto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fotoSequence")
	private Long codigo;
	
	@Column(name = "nm_foto_original")
	private String nomeOriginal;
	
	@Column(name = "nm_foto")
	private String novoNome;
	
	@Column(name = "vl_foto")
	private String link;
	
	@Column(name = "ds_tipo")
	private String tipo;
	
	@Lob
	@Column(name = "fl_foto", length = 1000)
	private byte[] fotoByte;
	
	@ManyToOne
	@JoinColumn(name = "cd_usuario_cliente", nullable = true)
	@JsonBackReference(value="cliente-foto")
	private UsuarioCliente usuarioCliente;
	
	@ManyToOne
	@JoinColumn(name = "cd_mascote", nullable = true)
	@JsonBackReference(value="mascote-foto")
	private Mascote mascote;

	public Foto(String nomeOriginal, String novoNome, String tipo, byte[] fotoByte) {
		this.nomeOriginal = nomeOriginal;
		this.novoNome = novoNome;
		this.tipo = tipo;
		this.fotoByte = fotoByte;
	}
	
	
}
