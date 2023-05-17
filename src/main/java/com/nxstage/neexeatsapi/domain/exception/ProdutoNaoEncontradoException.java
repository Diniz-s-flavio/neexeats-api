package com.nxstage.neexeatsapi.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{
    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId){
        this(String.format(
                "O Produto de código %d não Existe no Restaurante de código %d",produtoId,restauranteId
        ));
    }
}
