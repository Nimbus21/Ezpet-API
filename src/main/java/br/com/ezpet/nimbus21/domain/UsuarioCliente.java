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
@Entity
@Table(name = "TB_USUARIO_CLIENTE")
@SequenceGenerator(name = "usuarioSequence", sequenceName = "SQ_USUARIO", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCliente {

	@Id
	@Column(name = "cd_usuario_cliente")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioSequence")
	private Long codigo;

	@Column(name = "nm_usuario_cliente")
	private String nome;
	
	@Column(name = "vl_email_cliente")
	private String email;
	
	@Column(name = "tx_senha_cliente")
	private String senha;
	
}
