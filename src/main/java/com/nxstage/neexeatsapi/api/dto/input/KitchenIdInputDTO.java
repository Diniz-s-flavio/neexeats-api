package com.nxstage.neexeatsapi.api.dto.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class KitchenIdInputDTO {

    @NotNull
    private Long id;
}
