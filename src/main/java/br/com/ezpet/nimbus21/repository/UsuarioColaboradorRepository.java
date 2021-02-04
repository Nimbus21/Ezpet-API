package br.com.ezpet.nimbus21.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ezpet.nimbus21.domain.UsuarioColaborador;

public interface UsuarioColaboradorRepository extends JpaRepository<UsuarioColaborador, Long> {
	Optional<UsuarioColaborador> findByEmail(String email);
}
