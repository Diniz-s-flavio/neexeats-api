package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.exception.NegocioException;
import com.nxstage.neexeatsapi.domain.model.Pedido;
import com.nxstage.neexeatsapi.domain.model.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {
    @Autowired
    private EmissaoPerdidoService emissaoPerdido;
    @Transactional
    public void confirmar(Long pedidoId){
        Pedido pedido = emissaoPerdido.buscarOuFalhar(pedidoId);

        if (!pedido.getStatus().equals(StatusPedido.CRIADO)){
            throw new NegocioException(
                    String.format("Status do Pedido %d não pode ser alterado de %s para %s",
                            pedido.getId(),
                            pedido.getStatus().getDescricao(),
                            StatusPedido.CONFIRMADO.getDescricao()));
        }

        pedido.setStatus(StatusPedido.CONFIRMADO);
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }
    @Transactional
    public void cancelar(Long pedidoId){
        Pedido pedido = emissaoPerdido.buscarOuFalhar(pedidoId);

        if (!pedido.getStatus().equals(StatusPedido.CRIADO)){
            throw new NegocioException(
                    String.format("Status do Pedido %d não pode ser alterado de %s para %s",
                            pedido.getId(),
                            pedido.getStatus().getDescricao(),
                            StatusPedido.CANCELADO.getDescricao()));
        }

        pedido.setStatus(StatusPedido.CANCELADO);
        pedido.setDataCancelamento(OffsetDateTime.now());
    }
    @Transactional
    public void entregar(Long pedidoId){
        Pedido pedido = emissaoPerdido.buscarOuFalhar(pedidoId);

        if (!pedido.getStatus().equals(StatusPedido.CONFIRMADO)){
            throw new NegocioException(
                    String.format("Status do Pedido %d não pode ser alterado de %s para %s",
                            pedido.getId(),
                            pedido.getStatus().getDescricao(),
                            StatusPedido.ENTREGUE.getDescricao()));
        }

        pedido.setStatus(StatusPedido.ENTREGUE);
        pedido.setDataEntrega(OffsetDateTime.now());
    }
}
