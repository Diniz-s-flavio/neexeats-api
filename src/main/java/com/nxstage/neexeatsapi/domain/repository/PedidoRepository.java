package com.nxstage.neexeatsapi.domain.repository;

import com.nxstage.neexeatsapi.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends
        CustomJpaRepository<Pedido,Long>,
        JpaSpecificationExecutor<Pedido> {
    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.kitchen")
    List<Pedido> findAll();

    Optional<Pedido> findByCodigo(String codigo);
}
