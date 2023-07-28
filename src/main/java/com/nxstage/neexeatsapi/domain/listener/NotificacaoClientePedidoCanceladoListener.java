package com.nxstage.neexeatsapi.domain.listener;

import com.nxstage.neexeatsapi.domain.event.PedidoCanceladoEvent;
import com.nxstage.neexeatsapi.domain.model.Pedido;
import com.nxstage.neexeatsapi.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoClientePedidoCanceladoListener {

    @Autowired
    private EnvioEmailService envioEmail;
    @TransactionalEventListener
    public void whenCanceledOrder(PedidoCanceladoEvent event){

        Pedido pedido = event.getPedido();

        var message = EnvioEmailService.Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido Cancelado")
                .variavel("pedido",pedido)
                .corpo("pedido-cancelado.html")
                .destinatario(pedido.getCliente().getEmail()).build();

        envioEmail.send(message);
    }
}
