package com.nxstage.neexeatsapi.api.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class ItemPedidoInputDTO {
    @NotNull
    private Long produtoId;
    @NotNull
    @Positive
    private Integer quantidade;
    private String observacao;
}
