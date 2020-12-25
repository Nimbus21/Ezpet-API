package br.com.ezpet.nimbus21.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ezpet.nimbus21.domain.Mascote;

public interface MascoteRepository extends JpaRepository<Mascote, Long>{
	List<Mascote> findByNomeContaining(String nome);
}
