package com.nxstage.neexeatsapi.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static  final  long serialVersionUID = 2L;

    public CidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public CidadeNaoEncontradaException(Long cidadeId){
        this(String.format("O Cidade de código %d não Existe",cidadeId));
    }
}
