package com.nxstage.neexeatsapi.api.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class PermissaoDTO {
    private Long id;
    private String nome;
    private String descricao;
}
