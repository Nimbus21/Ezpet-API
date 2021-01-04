package br.com.ezpet.nimbus21.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ezpet.nimbus21.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
