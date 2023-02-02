package com.nxstage.neexeatsapi.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
    private static  final  long serialVersionUID = 2L;

    public RestauranteNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public RestauranteNaoEncontradoException(Long restauranteId){
        this(String.format("O Restaurante de código %d não Existe",restauranteId));
    }
}
