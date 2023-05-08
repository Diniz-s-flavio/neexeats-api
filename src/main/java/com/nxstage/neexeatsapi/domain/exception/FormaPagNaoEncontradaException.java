package com.nxstage.neexeatsapi.domain.exception;

public class FormaPagNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static  final  long serialVersionUID = 2L;

    public FormaPagNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public FormaPagNaoEncontradaException(Long formaPagId){
        this(String.format("O Forma de Pagamento de código %d não Existe",formaPagId));
    }
}
