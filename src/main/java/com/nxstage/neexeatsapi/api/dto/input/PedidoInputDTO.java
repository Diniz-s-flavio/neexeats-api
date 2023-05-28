package com.nxstage.neexeatsapi.api.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class PedidoInputDTO {
    @NotNull
    @Valid
    private RestauranteIdInputDTO restaurante;
    @NotNull
    @Valid
    private FormaPagIdInputDTO formaPag;
    @NotNull
    @Valid
    private EnderecoInputDTO enderecoEntrega;
    @NotNull
    @Valid
    @Size(min = 1)
    private List<ItemPedidoInputDTO> itens;
}
