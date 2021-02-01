package br.com.ezpet.nimbus21.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(
			name = "TB_USUARIOS_ADMIN_ROLES", 
			joinColumns = @JoinColumn(
					name = "cd_usuario_admin", referencedColumnName = "cd_usuario_admin"),
			inverseJoinColumns = @JoinColumn(
					name = "cd_role", referencedColumnName = "cd_role"))
	private Collection<Role> roles;

	public UsuarioAdmin(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	public UsuarioAdmin(String email, String senha, Collection<Role> roles) {
		this.email = email;
		this.senha = senha;
		this.roles = roles;
	}
	
	
	
}
