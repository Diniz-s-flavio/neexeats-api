package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {
    @Autowired
    private EmissaoPerdidoService emissaoPerdido;
    @Autowired
    private EnvioEmailService envioEmail;
    @Transactional
    public void confirmar(String pedidoCode){
        Pedido pedido = emissaoPerdido.buscarOuFalhar(pedidoCode);

        pedido.confirmar();
        var message = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .variavel("pedido",pedido)
                .corpo("pedido-confirmado.html")
                .destinatario(pedido.getCliente().getEmail()).build();

        envioEmail.send(message);
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
