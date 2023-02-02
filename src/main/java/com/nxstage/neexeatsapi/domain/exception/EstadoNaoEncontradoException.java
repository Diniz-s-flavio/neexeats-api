package com.nxstage.neexeatsapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
    private static  final  long serialVersionUID = 2L;

    public EstadoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public EstadoNaoEncontradoException(Long estadoId){
        this(String.format("O Estado de código %d não Existe",estadoId));
    }
}
