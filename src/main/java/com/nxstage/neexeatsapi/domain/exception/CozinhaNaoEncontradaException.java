package com.nxstage.neexeatsapi.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static  final  long serialVersionUID = 2L;

    public CozinhaNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public CozinhaNaoEncontradaException(Long kitchenId){
        this(String.format("O Cozinha de código %d não Existe",kitchenId));
    }
}
