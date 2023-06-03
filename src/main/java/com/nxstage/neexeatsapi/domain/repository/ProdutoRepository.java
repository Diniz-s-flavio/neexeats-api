package com.nxstage.neexeatsapi.domain.repository;

import com.nxstage.neexeatsapi.domain.model.Produto;
import com.nxstage.neexeatsapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>{
    @Query(nativeQuery = true, value = "SELECT * FROM produto WHERE restaurante_id = :restauranteId AND id = :produto")
    Optional<Produto> findById(@Param("restauranteId") Long restauranteId, @Param("produto") Long produto);

    List<Produto> findAllByRestaurante(Restaurante restaurante);
    @Query("from Produto as p where p.restaurante = :restaurante and p.ativo = true")
    List<Produto> findActiveByRestaurante(Restaurante restaurante);
}
