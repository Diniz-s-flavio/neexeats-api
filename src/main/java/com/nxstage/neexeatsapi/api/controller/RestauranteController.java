package com.nxstage.neexeatsapi.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.nxstage.neexeatsapi.api.assembler.RestauranteModelAssembler;
import com.nxstage.neexeatsapi.api.assembler.disassembler.RestauranteInputDisassembler;
import com.nxstage.neexeatsapi.api.dto.RestauranteDTO;
import com.nxstage.neexeatsapi.api.dto.input.RestauranteInputDTO;
import com.nxstage.neexeatsapi.api.dto.view.RestauranteView;
import com.nxstage.neexeatsapi.domain.exception.CidadeNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.exception.CozinhaNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.exception.NegocioException;
import com.nxstage.neexeatsapi.domain.exception.RestauranteNaoEncontradoException;
import com.nxstage.neexeatsapi.domain.model.Restaurante;
import com.nxstage.neexeatsapi.domain.repository.RestauranteRepository;
import com.nxstage.neexeatsapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;


    @GetMapping()
    @JsonView(RestauranteView.Resumo.class)
    public List<RestauranteDTO> restauranteList(){
        return restauranteModelAssembler.toCollectionDTO(
                restauranteRepository.findAll());
    }
    //@GetMapping(params = "projecao=apenas-nome")
    //@JsonView(RestauranteView.ApenasNome.class)
    //public List<RestauranteDTO> restauranteNomeList(){
    //    return restauranteList();
    //}

    @GetMapping("/{restauranteId}")
    public RestauranteDTO buscar(@PathVariable("restauranteId") Long id){
        Restaurante restaurante = cadastroRestaurante.buscaOuFalhar(id);

        return restauranteModelAssembler.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO restauranteAdd(@RequestBody @Valid RestauranteInputDTO restauranteInput){
        Restaurante restaurante = restauranteInputDisassembler
                .toDomainObject(restauranteInput);

        RestauranteDTO restauranteDTO = restauranteModelAssembler
                .toModel(cadastroRestaurante.salvar(restaurante));
        try{
            return restauranteDTO;
        }catch(CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteDTO restauranteUpdate(
            @PathVariable("restauranteId") Long id, @RequestBody @Valid RestauranteInputDTO restauranteInput) {
            Restaurante updatingRestaurante = cadastroRestaurante.buscaOuFalhar(id);

            restauranteInputDisassembler.copyToDomainObject(restauranteInput,updatingRestaurante);

        try{
            return restauranteModelAssembler.toModel(
                    cadastroRestaurante.salvar(updatingRestaurante));
        }catch(CozinhaNaoEncontradaException | CidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    //----------------------------------------------Ativação----------------------------------------------//

    @PutMapping("/{restauranteId}/ativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar (@PathVariable("restauranteId") Long restauranteId){
        cadastroRestaurante.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/ativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar (@PathVariable("restauranteId") Long restauranteId){
        cadastroRestaurante.inativar(restauranteId);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activateRestauranteList(@RequestBody List<Long> restaurantesIds){
        try{
            cadastroRestaurante.ativar(restaurantesIds);
        }catch(RestauranteNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }
    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unactivateRestauranteList(@RequestBody List<Long> restaurantesIds){
        try{
            cadastroRestaurante.inativar(restaurantesIds);
        }catch(RestauranteNaoEncontradoException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    //----------------------------------------------Abertura----------------------------------------------//

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void openRestaurante(@PathVariable Long restauranteId){
        cadastroRestaurante.openRestaurante(restauranteId);
    }
    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void closeRestaurante(@PathVariable Long restauranteId){
        cadastroRestaurante.closeRestaurante(restauranteId);
    }
}
