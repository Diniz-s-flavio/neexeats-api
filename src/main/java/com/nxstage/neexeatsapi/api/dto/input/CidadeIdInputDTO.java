package com.nxstage.neexeatsapi.api.dto.input;

import com.nxstage.neexeatsapi.api.dto.EstadoDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CidadeIdInputDTO {
    @NotNull
    private Long id;
}
