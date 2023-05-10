package com.nxstage.neexeatsapi.domain.repository;

import com.nxstage.neexeatsapi.domain.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitchenRepository extends CustomJpaRepository<Kitchen, Long> {
    List<Kitchen> findByNomeContaining(String nome);

    Optional<Kitchen> findUmaByNome(String Nome);

    boolean existsByNome(String nome);
}
