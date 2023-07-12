package com.nxstage.neexeatsapi.api.dto;

import lombok.Data;

@Data
public class FotoProdutoDTO {
    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
