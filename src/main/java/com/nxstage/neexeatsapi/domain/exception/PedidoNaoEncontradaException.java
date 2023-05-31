package com.nxstage.neexeatsapi.domain.exception;

public class PedidoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static  final  long serialVersionUID = 1L;

    public PedidoNaoEncontradaException(String pedidoCode){
        super(String.format("O Pedido de código %s não Existe",pedidoCode));
    }
}
