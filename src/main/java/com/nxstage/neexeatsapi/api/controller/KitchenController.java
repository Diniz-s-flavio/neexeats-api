package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.domain.model.Kitchen;
import com.nxstage.neexeatsapi.domain.repository.KitchenRepository;
import com.nxstage.neexeatsapi.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Kitchen> kitchenList(){
        return kitchenRepository.findAll();
    }


    @GetMapping("/{cozinhaId}")
    public Kitchen buscar(@PathVariable("cozinhaId") long id){
        return cadastroCozinha.buscarOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen addKitchem(@RequestBody Kitchen kitchen){
        return cadastroCozinha.salvar(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public Kitchen updateKitchen(@PathVariable Long kitchenId,  @RequestBody Kitchen kitchen){
        Kitchen updatingKitchen = cadastroCozinha.buscarOuFalhar(kitchenId);

        BeanUtils.copyProperties(kitchen, updatingKitchen,"id");
        return cadastroCozinha.salvar(updatingKitchen);
    }

    @DeleteMapping("/{kitchenId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKitchen(@PathVariable Long kitchenId){
            cadastroCozinha.excluir(kitchenId);

    }
}

