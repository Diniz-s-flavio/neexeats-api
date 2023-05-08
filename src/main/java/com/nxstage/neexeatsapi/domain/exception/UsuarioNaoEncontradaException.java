package com.nxstage.neexeatsapi.domain.exception;

public class UsuarioNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static  final  long serialVersionUID = 1L;

    public UsuarioNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public UsuarioNaoEncontradaException(Long Id){
        this(String.format("O Usuario de código %d não Existe",Id));
    }
}
