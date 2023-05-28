package com.nxstage.neexeatsapi.api.assembler.disassembler;

import com.nxstage.neexeatsapi.api.dto.input.PedidoInputDTO;
import com.nxstage.neexeatsapi.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Pedido toDomainObject(PedidoInputDTO pedidoInput){
        return modelMapper.map(pedidoInput, Pedido.class);
    }

    public void copyToDomainObject(PedidoInputDTO pedidoInput, Pedido pedido){
        modelMapper.map(pedidoInput, pedido);
    }
}
