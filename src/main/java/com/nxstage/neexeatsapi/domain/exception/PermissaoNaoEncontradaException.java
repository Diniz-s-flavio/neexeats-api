package com.nxstage.neexeatsapi.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static  final  long serialVersionUID = 1L;

    public PermissaoNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public PermissaoNaoEncontradaException(Long permissaoId){
        this(String.format("A Permissão de código %d não Existe",permissaoId));
    }
}
