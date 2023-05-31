package com.nxstage.neexeatsapi.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.nxstage.neexeatsapi.api.dto.view.RestauranteView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KitchenDTO {
    @JsonView(RestauranteView.Resumo.class)
    private Long id;
    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
