package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.KitchenModelAssembler;
import com.nxstage.neexeatsapi.api.assembler.disassembler.KitchenInputDisassembler;
import com.nxstage.neexeatsapi.api.dto.KitchenDTO;
import com.nxstage.neexeatsapi.api.dto.input.KitchenInputDTO;
import com.nxstage.neexeatsapi.domain.model.Kitchen;
import com.nxstage.neexeatsapi.domain.repository.KitchenRepository;
import com.nxstage.neexeatsapi.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private KitchenModelAssembler kitchenModelAssembler;

    @Autowired
    private KitchenInputDisassembler kitchenInputDisassembler;

    @GetMapping
    public Page<KitchenDTO> kitchenList(@PageableDefault(size = 10) Pageable pageable){
        Page<Kitchen> kitchenPage = kitchenRepository.findAll(pageable);
        List<KitchenDTO> kitchenDTOList = kitchenModelAssembler.toCollectionDTO(kitchenPage.getContent());
        Page<KitchenDTO> kitchenDTOPage= new PageImpl<>(kitchenDTOList, pageable,
                kitchenPage.getTotalElements());

        return  kitchenDTOPage;
    }


    @GetMapping("/{cozinhaId}")
    public KitchenDTO buscar(@PathVariable("cozinhaId") Long id){
        return kitchenModelAssembler.toModel(
                cadastroCozinha.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenDTO addKitchem(@RequestBody @Valid KitchenInputDTO kitchenInput){
        Kitchen kitchen = kitchenInputDisassembler.toDomainObject(kitchenInput);
        return kitchenModelAssembler.toModel(
                cadastroCozinha.salvar(kitchen));
    }

    @PutMapping("/{kitchenId}")
    public KitchenDTO updateKitchen(@PathVariable Long kitchenId,  @RequestBody @Valid KitchenInputDTO kitchenInput){
        Kitchen updatingKitchen = cadastroCozinha.buscarOuFalhar(kitchenId);

        kitchenInputDisassembler.copyToDomainObject(kitchenInput,updatingKitchen);

        return kitchenModelAssembler.toModel(cadastroCozinha.salvar(updatingKitchen));
    }

    @DeleteMapping("/{kitchenId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKitchen(@PathVariable Long kitchenId){
            cadastroCozinha.excluir(kitchenId);

    }
}

