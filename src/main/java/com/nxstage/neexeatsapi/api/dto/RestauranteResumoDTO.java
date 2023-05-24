package com.nxstage.neexeatsapi.api.dto;

import com.nxstage.neexeatsapi.domain.model.Restaurante;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestauranteResumoDTO {
    private Long id;
    private String nome;

    public RestauranteResumoDTO(Restaurante entity) {
        id = entity.getId();
        nome = entity.getNome();
    }
}
