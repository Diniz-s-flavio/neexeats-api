package com.nxstage.neexeatsapi.api.dto;

import com.nxstage.neexeatsapi.domain.model.Pedido;
import com.nxstage.neexeatsapi.domain.model.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class ItemPedidoDTO {
    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private String observacao;


}
