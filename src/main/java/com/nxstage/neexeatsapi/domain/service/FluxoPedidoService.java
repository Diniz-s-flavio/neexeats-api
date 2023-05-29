package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {
    @Autowired
    private EmissaoPerdidoService emissaoPerdido;
    @Transactional
    public void confirmar(Long pedidoId){
        Pedido pedido = emissaoPerdido.buscarOuFalhar(pedidoId);

        pedido.confirmar();
    }
    @Transactional
    public void cancelar(Long pedidoId){
        Pedido pedido = emissaoPerdido.buscarOuFalhar(pedidoId);

        pedido.cancelar();
    }
    @Transactional
    public void entregar(Long pedidoId){
        Pedido pedido = emissaoPerdido.buscarOuFalhar(pedidoId);

        pedido.enviar();
    }
}
