package com.nxstage.neexeatsapi.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {
    private static  final  long serialVersionUID = 2L;

    public GrupoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public GrupoNaoEncontradoException(Long id){
        this(String.format("O Grupo de código %d não Existe",id));
    }
}
