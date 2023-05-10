package com.nxstage.neexeatsapi.domain.repository;

import com.nxstage.neexeatsapi.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository  extends CustomJpaRepository<Cidade, Long> {

}
