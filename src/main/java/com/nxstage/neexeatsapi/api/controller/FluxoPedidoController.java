package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{pedidoCode}")
public class FluxoPedidoController {
    @Autowired
    private FluxoPedidoService fluxoPedido;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmar(@PathVariable String pedidoCode){
        fluxoPedido.confirmar(pedidoCode);
    }
    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable String pedidoCode){
        fluxoPedido.cancelar(pedidoCode);
    }
    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregar(@PathVariable String pedidoCode){
        fluxoPedido.entregar(pedidoCode);
    }
}

