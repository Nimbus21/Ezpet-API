package br.com.ezpet.nimbus21.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ezpet.nimbus21.domain.UsuarioComercial;

public interface UsuarioComercialRepository extends JpaRepository<UsuarioComercial, Long>{
	Optional<UsuarioComercial> findByEmail(String email);
	
	@Query("FROM UsuarioComercial u " + "WHERE LOWER(u.nome) like %:searchTerm% " + "OR LOWER(u.email) like %:searchTerm%")
	Page<UsuarioComercial> search(@Param("searchTerm") String searchTerm, Pageable pageable);
	
	List<UsuarioComercial> findAllByNome(String nome, Pageable pageable);
}
