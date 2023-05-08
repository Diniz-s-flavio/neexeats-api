package com.nxstage.neexeatsapi.api.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UsuarioDTO {

    private Long id;

    private String nome;

    private String email;
}
