package com.nxstage.neexeatsapi.api.dto.input;

import com.nxstage.neexeatsapi.api.dto.CidadeResumoDTO;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EnderecoInputDTO {

    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String numero;
    private String complemento;
    @NotBlank
    private String bairro;
    @Valid
    @NotNull
    private CidadeIdInputDTO cidade;
}
