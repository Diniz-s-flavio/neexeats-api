package com.nxstage.neexeatsapi.api.dto;

import com.nxstage.neexeatsapi.domain.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PedidoDTO {
    private Long id;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataEntrega;
    private RestauranteResumoDTO restaurante;
    private UsuarioDTO cliente;
    private FormaPagDTO formaPag;
    private EnderecoDTO enderecoEntrega;
    private List<ItemPedidoDTO> itens= new ArrayList<>();

}
