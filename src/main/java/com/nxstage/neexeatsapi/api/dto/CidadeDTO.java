package com.nxstage.neexeatsapi.api.dto;

import com.nxstage.neexeatsapi.api.dto.input.EstadoIdInputDTO;
import lombok.Data;

@Data
public class CidadeDTO {
    private Long id;
    private String nome;
    private EstadoDTO estado;
}
