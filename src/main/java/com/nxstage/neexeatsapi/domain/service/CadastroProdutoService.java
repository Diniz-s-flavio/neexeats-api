package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.exception.EntidadeEmUsoException;
import com.nxstage.neexeatsapi.domain.exception.FormaPagNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.exception.ProdutoNaoEncontradoException;
import com.nxstage.neexeatsapi.domain.model.Produto;
import com.nxstage.neexeatsapi.domain.model.Restaurante;
import com.nxstage.neexeatsapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId){
        return produtoRepository.findById(restauranteId,produtoId).orElseThrow(()->
                new ProdutoNaoEncontradoException(restauranteId,produtoId));
    }

    @Transactional
    public Produto salvar(Produto produto){
        return  produtoRepository.save(produto);
    }

    @Transactional(readOnly = true)
    public List<Produto> findByRestaurante(Restaurante restaurante){
        return produtoRepository.findByRestaurante(restaurante);
    }
}
