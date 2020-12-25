package br.com.ezpet.nimbus21.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ezpet.nimbus21.domain.UsuarioCliente;

public interface UsuarioClienteRepository extends JpaRepository<UsuarioCliente, Long>{
	List<UsuarioCliente> findByNomeContaining(String nome);
}
