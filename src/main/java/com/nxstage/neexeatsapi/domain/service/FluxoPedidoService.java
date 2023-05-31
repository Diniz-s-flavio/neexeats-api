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
    public void confirmar(String pedidoCode){
        Pedido pedido = emissaoPerdido.buscarOuFalhar(pedidoCode);

        pedido.confirmar();
    }
    @Transactional
    public void cancelar(String pedidoCode){
        Pedido pedido = emissaoPerdido.buscarOuFalhar(pedidoCode);

        pedido.cancelar();
    }
    @Transactional
    public void entregar(String pedidoCode){
        Pedido pedido = emissaoPerdido.buscarOuFalhar(pedidoCode);

        pedido.enviar();
    }
}
