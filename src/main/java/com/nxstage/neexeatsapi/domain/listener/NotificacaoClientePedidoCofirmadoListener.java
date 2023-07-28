package com.nxstage.neexeatsapi.domain.listener;

import com.nxstage.neexeatsapi.domain.event.PedidoConfirmadoEvent;
import com.nxstage.neexeatsapi.domain.model.Pedido;
import com.nxstage.neexeatsapi.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoClientePedidoCofirmadoListener {

    @Autowired
    private EnvioEmailService envioEmail;
    @TransactionalEventListener
    public void whenCofirmedOrder(PedidoConfirmadoEvent event){

        Pedido pedido = event.getPedido();

        var message = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .variavel("pedido",pedido)
                .corpo("pedido-confirmado.html")
                .destinatario(pedido.getCliente().getEmail()).build();

        envioEmail.send(message);
    }
}
