package com.nxstage.neexeatsapi.domain.repository;

import com.nxstage.neexeatsapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
        RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {


    @Query("from Restaurante  r join fetch r.kitchen")
    List<Restaurante> findAll();

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal TaxaFinal);

    //@Query("from Restaurante where nome like %:nome% and kitchen.id = :id")
    List<Restaurante> buscarPorNomeCozinha(String nome, @Param("id") Long kitchen);

    //List<Restaurante> findByNomeContainingAndKitchenId(String nome, Long kitchen);
    Optional<Restaurante> findFirstByNomeContaining(String nome);

    List<Restaurante> findTop2ByNomeContaining(String nome);

    int countByKitchenId(Long kitchen);

}
