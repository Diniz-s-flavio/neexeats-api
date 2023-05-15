package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.FormaPagModelAssembler;
import com.nxstage.neexeatsapi.api.dto.FormaPagDTO;
import com.nxstage.neexeatsapi.domain.model.Restaurante;
import com.nxstage.neexeatsapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagController {
    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private FormaPagModelAssembler formaPagModelAssembler;
    @GetMapping
    public List<FormaPagDTO> listFomaPagByRestaurante(@PathVariable Long restauranteId){
        Restaurante restaurante = cadastroRestaurante.buscaOuFalhar(restauranteId);

        return formaPagModelAssembler.toCollectionDTO(restaurante.getFormaPag());
    }

    @DeleteMapping("/{formaPagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unlinkFormaPag(@PathVariable Long restauranteId, @PathVariable Long formaPagId){
        cadastroRestaurante.desassociarFormasPag(restauranteId,formaPagId);
    }

    @PutMapping("/{formaPagId}")
    public  void linkFormaPag(@PathVariable Long restauranteId, @PathVariable Long formaPagId){
        cadastroRestaurante.associarFormaPag(restauranteId,formaPagId);
    }
}
