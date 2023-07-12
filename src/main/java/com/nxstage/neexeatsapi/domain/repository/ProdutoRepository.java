package com.nxstage.neexeatsapi.domain.repository;

import com.nxstage.neexeatsapi.domain.model.FotoProduto;
import com.nxstage.neexeatsapi.domain.model.Produto;
import com.nxstage.neexeatsapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>, ProdutoRepositoryQueries{
    @Query(nativeQuery = true, value = "SELECT * FROM produto WHERE restaurante_id = :restauranteId AND id = :produto")
    Optional<Produto> findById(@Param("restauranteId") Long restauranteId, @Param("produto") Long produto);

    List<Produto> findAllByRestaurante(Restaurante restaurante);
    @Query("from Produto as p where p.restaurante = :restaurante and p.ativo = true")
    List<Produto> findActiveByRestaurante(Restaurante restaurante);

    @Query("select f from FotoProduto f join f.produto p "
            + "where p.restaurante.id = :restauranteId and f.produto.id = :produtoId")
    Optional<FotoProduto> findPhotoById(Long restauranteId, Long produtoId);
}
