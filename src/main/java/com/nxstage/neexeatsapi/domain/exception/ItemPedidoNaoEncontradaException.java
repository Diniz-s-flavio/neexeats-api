package com.nxstage.neexeatsapi.domain.exception;

public class ItemPedidoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static  final  long serialVersionUID = 2L;

    public ItemPedidoNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public ItemPedidoNaoEncontradaException(Long itemPedidoId){
        this(String.format("O Item do Pedido de código %d não Existe",itemPedidoId));
    }
}
