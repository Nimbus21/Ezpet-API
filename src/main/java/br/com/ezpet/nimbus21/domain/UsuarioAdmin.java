package br.com.ezpet.nimbus21.domain;

import java.util.Collection;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;
import lombok.NoArgsConstructor;

import br.com.ezpet.nimbus21.domain.tipos.Role;

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
	
	@Column(name = "ds_email")
	private String email;
	
	@Column(name = "tx_senha")
	private String senha;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ds_role")
	private Role role = Role.ROLE_ADMIN;

	public UsuarioAdmin(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
}
