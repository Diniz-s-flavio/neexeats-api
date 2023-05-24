package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.KitchenModelAssembler;
import com.nxstage.neexeatsapi.api.assembler.PedidoModelAssembler;
import com.nxstage.neexeatsapi.api.assembler.PedidoResumoModelAssembler;
import com.nxstage.neexeatsapi.api.assembler.disassembler.KitchenInputDisassembler;
import com.nxstage.neexeatsapi.api.dto.KitchenDTO;
import com.nxstage.neexeatsapi.api.dto.PedidoDTO;
import com.nxstage.neexeatsapi.api.dto.PedidoResumoDTO;
import com.nxstage.neexeatsapi.api.dto.input.KitchenInputDTO;
import com.nxstage.neexeatsapi.domain.model.Kitchen;
import com.nxstage.neexeatsapi.domain.repository.KitchenRepository;
import com.nxstage.neexeatsapi.domain.service.CadastroCozinhaService;
import com.nxstage.neexeatsapi.domain.service.CadastroPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private CadastroPedidoService cadastroPedido;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;
    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @GetMapping
    public List<PedidoResumoDTO> listPedido(){
        return pedidoResumoModelAssembler.toCollectionDTO(
                cadastroPedido.findAll());
    }


    @GetMapping("/{pedidoId}")
    public PedidoDTO buscar(@PathVariable("pedidoId") Long pedidoId){
        return pedidoModelAssembler.toModel(
                cadastroPedido.buscarOuFalhar(pedidoId));
    }

    /*@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenDTO addKitchem(@RequestBody @Valid KitchenInputDTO kitchenInput){
        Kitchen kitchen = kitchenInputDisassembler.toDomainObject(kitchenInput);
        return kitchenModelAssembler.toModel(
                cadastroCozinha.salvar(kitchen));
    }*/
}

