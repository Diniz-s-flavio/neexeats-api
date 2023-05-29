package com.nxstage.neexeatsapi.domain.model;

public enum StatusPedido {
    CRIADO("Criado"),
    CONFIRMADO("Confirmado"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private String descricao;

    StatusPedido(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
