package com.nxstage.neexeatsapi.api.dto;

import com.nxstage.neexeatsapi.domain.model.Permissao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class GrupoDTO {

    private Long id;

    private String nome;
}
