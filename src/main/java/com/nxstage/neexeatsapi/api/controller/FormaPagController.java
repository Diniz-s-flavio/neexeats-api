package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.FormaPagInputDisassembler;
import com.nxstage.neexeatsapi.api.assembler.FormaPagModelAssembler;
import com.nxstage.neexeatsapi.api.dto.FormaPagDTO;
import com.nxstage.neexeatsapi.api.dto.input.FormaPagInputDTO;
import com.nxstage.neexeatsapi.domain.model.FormaPag;
import com.nxstage.neexeatsapi.domain.repository.FormaPagRepository;
import com.nxstage.neexeatsapi.domain.service.CadastroFormaPagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formas-pag")
public class FormaPagController {
    @Autowired
    private CadastroFormaPagService cadastroFormaPag;

    @Autowired
    private FormaPagRepository formaPagRepository;

    @Autowired
    private FormaPagModelAssembler formaPagModelAssembler;

    @Autowired
    private FormaPagInputDisassembler formaPagInputDisassembler;

    @GetMapping
    public List<FormaPagDTO> formaPagList(){
        return formaPagModelAssembler.toCollectionDTO(formaPagRepository.findAll());
    }

    @GetMapping("/{formaPagId}")
    public FormaPagDTO buscar(@PathVariable("formaPagId") Long formaPagId){
        return formaPagModelAssembler.toModel(
                cadastroFormaPag.buscarOuFalhar(formaPagId));
    }

    @PostMapping
    public FormaPagDTO formaPagAdd(@RequestBody @Valid FormaPagInputDTO formaPagInput){
        return formaPagModelAssembler.toModel(
                cadastroFormaPag.salvar(
                        formaPagInputDisassembler.toDomainObject(formaPagInput)));
    }

    @PutMapping("/{formaPagId}")
    public FormaPagDTO formaPagUpdate(@PathVariable("formaPagId") Long formaPagId,
                                      @RequestBody FormaPagInputDTO formaPagInput){
        FormaPag formaPag = cadastroFormaPag.buscarOuFalhar(formaPagId);
        formaPagInputDisassembler.copyToDomainObject(formaPagInput,formaPag);

        return formaPagModelAssembler.toModel(
                cadastroFormaPag.salvar(formaPag));
    }

    @DeleteMapping("/{formaPagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void formaPagtDelete(@PathVariable("formaPagId") Long formaPagId){
        cadastroFormaPag.excluir(formaPagId);
    }
}
