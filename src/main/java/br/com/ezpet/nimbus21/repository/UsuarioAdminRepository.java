package br.com.ezpet.nimbus21.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ezpet.nimbus21.domain.UsuarioAdmin;

public interface UsuarioAdminRepository extends JpaRepository<UsuarioAdmin, Long>{
	Optional<UsuarioAdmin> findByEmail(String email);
}
