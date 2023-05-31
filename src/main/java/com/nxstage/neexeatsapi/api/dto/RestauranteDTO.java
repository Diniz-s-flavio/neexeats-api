package com.nxstage.neexeatsapi.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.nxstage.neexeatsapi.api.dto.view.RestauranteView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteDTO {
    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private Long id;
    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
    private String nome;
    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;
    @JsonView(RestauranteView.Resumo.class)
    private KitchenDTO kitchen;
    private Boolean ativo;
    private Boolean aberto;
    private EnderecoDTO endereco;
}
