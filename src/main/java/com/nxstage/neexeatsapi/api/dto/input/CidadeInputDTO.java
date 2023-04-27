package com.nxstage.neexeatsapi.api.dto.input;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
public class CidadeInputDTO {
    @NotBlank
    private String nome;
    @Valid
    private EstadoIdInputDTO estado;
}
