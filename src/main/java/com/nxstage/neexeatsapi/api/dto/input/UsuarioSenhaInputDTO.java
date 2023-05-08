package com.nxstage.neexeatsapi.api.dto.input;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UsuarioSenhaInputDTO {
    @NotBlank
    private String senha;
    @NotBlank
    private String novaSenha;
}
