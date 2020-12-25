package br.com.ezpet.nimbus21.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ezpet.nimbus21.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	List<Produto> findByTipoProdutoContaining(String tipoProduto);
}
