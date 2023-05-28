package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.PedidoModelAssembler;
import com.nxstage.neexeatsapi.api.assembler.PedidoResumoModelAssembler;
import com.nxstage.neexeatsapi.api.assembler.disassembler.PedidoInputDisassembler;
import com.nxstage.neexeatsapi.api.dto.PedidoDTO;
import com.nxstage.neexeatsapi.api.dto.PedidoResumoDTO;
import com.nxstage.neexeatsapi.api.dto.input.PedidoInputDTO;
import com.nxstage.neexeatsapi.domain.exception.EntidadeNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.exception.NegocioException;
import com.nxstage.neexeatsapi.domain.model.Pedido;
import com.nxstage.neexeatsapi.domain.model.Usuario;
import com.nxstage.neexeatsapi.domain.repository.PedidoRepository;
import com.nxstage.neexeatsapi.domain.service.CadastroUsuarioService;
import com.nxstage.neexeatsapi.domain.service.EmissaoPerdidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private EmissaoPerdidoService emissaoPerdido;
    @Autowired
    private CadastroUsuarioService cadastroUsuario;
    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;
    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;
    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @GetMapping
    public List<PedidoResumoDTO> listPedido(){
        return pedidoResumoModelAssembler.toCollectionDTO(
                pedidoRepository.findAll());
    }


    @GetMapping("/{pedidoId}")
    public PedidoDTO buscar(@PathVariable("pedidoId") Long pedidoId){
        return pedidoModelAssembler.toModel(
                emissaoPerdido.buscarOuFalhar(pedidoId));
    }

    @PostMapping
    public PedidoDTO addPedido(@RequestBody @Valid PedidoInputDTO pedidoInput){
        try{
            Pedido newPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            //TODO pegar Usuario da autenticação
            newPedido.setCliente(new Usuario());
            newPedido.getCliente().setId(1L);

            newPedido = emissaoPerdido.emitir(newPedido);

            return pedidoModelAssembler.toModel(newPedido);
        }catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage(),e);
        }

    }
}

