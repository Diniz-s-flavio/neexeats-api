package com.nxstage.neexeatsapi.api.dto.input;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ProdutoInputDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    @PositiveOrZero
    private Double preco;
    @NotNull
    private Boolean ativo;
}
