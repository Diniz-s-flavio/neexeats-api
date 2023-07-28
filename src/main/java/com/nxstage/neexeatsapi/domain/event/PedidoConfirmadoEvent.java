package com.nxstage.neexeatsapi.domain.event;

import com.nxstage.neexeatsapi.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PedidoConfirmadoEvent {

    private Pedido pedido;
}
