package br.com.ezpet.nimbus21.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ezpet.nimbus21.domain.Foto;

public interface FotoRepository extends JpaRepository<Foto, Long>{
	Optional<Foto> findByNome(String nome);
}
