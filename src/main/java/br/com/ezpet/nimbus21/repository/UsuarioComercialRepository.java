package br.com.ezpet.nimbus21.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ezpet.nimbus21.domain.UsuarioComercial;

public interface UsuarioComercialRepository extends JpaRepository<UsuarioComercial, Long>{
	Optional<UsuarioComercial> findByEmail(String email);
}
