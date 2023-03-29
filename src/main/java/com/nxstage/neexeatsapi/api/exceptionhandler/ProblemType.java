package com.nxstage.neexeatsapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel","Mensagem Incompreensivel"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso","Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro interno de sistema"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parametro de URL inválido");

    private String title;
    private String uri;

    ProblemType(String path,String title){
        this.uri = "http://neexeat.com.br" + path;
        this.title = title;
    }
}
