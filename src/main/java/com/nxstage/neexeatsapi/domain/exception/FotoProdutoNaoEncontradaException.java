package com.nxstage.neexeatsapi.domain.exception;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException{
    public FotoProdutoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FotoProdutoNaoEncontradaException(Long restauranteId, Long produtoId){
        this(String.format(
                "Não Existe foto para o produto de código %d  no Restaurante de código %d",produtoId,restauranteId
        ));
    }
}
