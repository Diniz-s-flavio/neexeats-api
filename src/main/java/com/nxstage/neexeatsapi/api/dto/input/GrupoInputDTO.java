package com.nxstage.neexeatsapi.api.dto.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GrupoInputDTO {
    @NotBlank
    private String nome;
}
