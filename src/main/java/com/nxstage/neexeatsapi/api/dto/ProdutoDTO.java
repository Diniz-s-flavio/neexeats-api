package com.nxstage.neexeatsapi.api.dto;

import com.nxstage.neexeatsapi.domain.model.Produto;
import com.nxstage.neexeatsapi.domain.model.Restaurante;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProdutoDTO {
    private Long id;
    private  String nome;
    private String descricao;
    private BigDecimal preco;
    private  boolean ativo;
}
