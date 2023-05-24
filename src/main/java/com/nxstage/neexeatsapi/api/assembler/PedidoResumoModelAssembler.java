package com.nxstage.neexeatsapi.api.assembler;

import com.nxstage.neexeatsapi.api.dto.PedidoDTO;
import com.nxstage.neexeatsapi.api.dto.PedidoResumoDTO;
import com.nxstage.neexeatsapi.domain.model.Pedido;
import com.nxstage.neexeatsapi.domain.service.CadastroProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoResumoModelAssembler {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CadastroProdutoService cadastroProduto;

    public PedidoResumoDTO toModel(Pedido pedido){
        return modelMapper.map(pedido, PedidoResumoDTO.class);
    }

    public List<PedidoResumoDTO> toCollectionDTO(List<Pedido> pedidos){
        return pedidos.stream().map(pedido ->
                toModel(pedido)).collect(Collectors.toList());
    }

}
