package com.nxstage.neexeatsapi.domain.repository;

import com.nxstage.neexeatsapi.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {
    FotoProduto save(FotoProduto photo);
    void delete(FotoProduto photo);
}
