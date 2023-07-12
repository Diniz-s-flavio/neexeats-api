package com.nxstage.neexeatsapi.infrastructure.repository;

import com.nxstage.neexeatsapi.domain.model.FotoProduto;
import com.nxstage.neexeatsapi.domain.repository.ProdutoRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {
    @PersistenceContext
    private EntityManager manager;


    @Override
    @Transactional
    public FotoProduto save(FotoProduto photo) {
        return manager.merge(photo);
    }

    @Override
    @Transactional
    public void delete(FotoProduto photo) {
        manager.remove(photo);
    }
}
