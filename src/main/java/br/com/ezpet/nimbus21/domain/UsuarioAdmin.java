package br.com.ezpet.nimbus21.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ezpet.nimbus21.domain.tipos.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_USUARIO_ADMIN")
@SequenceGenerator(name = "usuarioAdminSequence", sequenceName = "SQ_USUARIO_ADMIN", allocationSize = 1)
public class UsuarioAdmin {

	@Id
	@Column(name = "cd_usuario_admin")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioAdminSequence")
	private Long codigo;
	
	@Column(name = "nm_usuario_admin")
	private String nome;
	
	@Column(name = "ds_email")
	private String email;
	
	@Column(name = "tx_senha")
	private String senha;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ds_role")
	private Role role = Role.ROLE_ADMIN;

	public UsuarioAdmin(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	
}
