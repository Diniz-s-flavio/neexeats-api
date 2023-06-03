package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.ProdutoModelAssembler;
import com.nxstage.neexeatsapi.api.assembler.disassembler.ProdutoInputDisassembler;
import com.nxstage.neexeatsapi.api.dto.ProdutoDTO;
import com.nxstage.neexeatsapi.api.dto.input.ProdutoInputDTO;
import com.nxstage.neexeatsapi.domain.model.Produto;
import com.nxstage.neexeatsapi.domain.model.Restaurante;
import com.nxstage.neexeatsapi.domain.service.CadastroProdutoService;
import com.nxstage.neexeatsapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoControlle {
    @Autowired
    private CadastroRestauranteService cadastroRestaurante;
    @Autowired
    private CadastroProdutoService cadastroProduto;
    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;
    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @GetMapping
    public List<ProdutoDTO> listProduto(
            @PathVariable("restauranteId") Long restauranteId,
            @RequestParam(required = false) boolean includeInactive){
        Restaurante restaurante = cadastroRestaurante.buscaOuFalhar(restauranteId);
        List<Produto> produtos = null;

        if (includeInactive){
            produtos = cadastroProduto.findAllByRestaurante(restaurante);
        }else {
            produtos = cadastroProduto.findActiveByRestaurante(restaurante);
        }

        return produtoModelAssembler.toCollectionDTO(produtos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoDTO findProduto(@PathVariable("restauranteId") Long restauranteId,
                                  @PathVariable("produtoId") Long produtoId){
        cadastroRestaurante.buscaOuFalhar(restauranteId);
        return produtoModelAssembler.toModel(
                cadastroProduto.buscarOuFalhar(restauranteId, produtoId));
    }

    @PostMapping
    public ProdutoDTO addProduto(@PathVariable("restauranteId") Long restauranteId,
                                 @RequestBody @Valid ProdutoInputDTO produtoInput){

        Restaurante restaurante = cadastroRestaurante.buscaOuFalhar(restauranteId);

        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);

        return produtoModelAssembler.toModel(
                cadastroProduto.salvar(produto
                ));
    }

    @PutMapping("/{produtoId}")
    public ProdutoDTO updateProduto(@PathVariable("restauranteId") Long restauranteId,
                                    @PathVariable("produtoId") Long produtoId,
                                    @RequestBody @Valid ProdutoInputDTO produtoInput){
        cadastroRestaurante.buscaOuFalhar(restauranteId);
        Produto produto = cadastroProduto.buscarOuFalhar(restauranteId,produtoId);

        produtoInputDisassembler.copyToDomainObject(produtoInput,produto);

        return produtoModelAssembler.toModel(cadastroProduto.salvar(produto));
    }
}
