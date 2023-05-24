package com.nxstage.neexeatsapi.domain.exception;

public class PedidoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static  final  long serialVersionUID = 1L;

    public PedidoNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public PedidoNaoEncontradaException(Long pedidoId){
        this(String.format("O Pedido de código %d não Existe",pedidoId));
    }
}
