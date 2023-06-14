package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.PedidoModelAssembler;
import com.nxstage.neexeatsapi.api.assembler.PedidoResumoModelAssembler;
import com.nxstage.neexeatsapi.api.assembler.disassembler.PedidoInputDisassembler;
import com.nxstage.neexeatsapi.api.dto.PedidoDTO;
import com.nxstage.neexeatsapi.api.dto.PedidoResumoDTO;
import com.nxstage.neexeatsapi.api.dto.input.PedidoInputDTO;
import com.nxstage.neexeatsapi.core.data.PageableTranslator;
import com.nxstage.neexeatsapi.domain.exception.EntidadeNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.exception.NegocioException;
import com.nxstage.neexeatsapi.domain.model.Pedido;
import com.nxstage.neexeatsapi.domain.model.Usuario;
import com.nxstage.neexeatsapi.domain.repository.PedidoRepository;
import com.nxstage.neexeatsapi.domain.repository.filter.PedidoFilter;
import com.nxstage.neexeatsapi.domain.service.CadastroUsuarioService;
import com.nxstage.neexeatsapi.domain.service.EmissaoPerdidoService;
import com.nxstage.neexeatsapi.infrastructure.repository.spec.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    public Page<PedidoResumoDTO> search(Pageable pageable, PedidoFilter pedidoFilter){
        Page<Pedido> pedidos = pedidoRepository.findAll(
                PedidoSpecs.useFilter(pedidoFilter),
                traduzirPageable(pageable));

        List<PedidoResumoDTO> pedidoResumoList = pedidoResumoModelAssembler.toCollectionDTO(pedidos.getContent());
        Page<PedidoResumoDTO> pedidoResumoPage = new PageImpl<>(pedidoResumoList,pageable,
                pedidos.getTotalElements());

        return  pedidoResumoPage;
    }


    @GetMapping("/{pedidoCode}")
    public PedidoDTO buscar(@PathVariable("pedidoCode") String pedidoCode){
        return pedidoModelAssembler.toModel(
                emissaoPerdido.buscarOuFalhar(pedidoCode));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
    private Pageable traduzirPageable(Pageable apiPageable){
        var mapping = Map.of(
                "codigo","codigo",
                "restaurante.nome", "restaurante.nome",
                "cliente.nome","cliente.nome",
                    "subTotal","subtotal",
                    "taxaFrete","TaxaFrete",
                    "valorTotal","valorTotal");

        return PageableTranslator.translate(apiPageable,mapping);
    }
}

