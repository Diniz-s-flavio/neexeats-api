package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.disassembler.GrupoInputDisassembler;
import com.nxstage.neexeatsapi.api.assembler.GrupoModelAssembler;
import com.nxstage.neexeatsapi.api.dto.GrupoDTO;
import com.nxstage.neexeatsapi.api.dto.input.GrupoInputDTO;
import com.nxstage.neexeatsapi.domain.model.Grupo;
import com.nxstage.neexeatsapi.domain.repository.GrupoRepository;
import com.nxstage.neexeatsapi.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupo;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @Autowired
    private GrupoInputDisassembler grupoInputDisassembler;

    @GetMapping
    public List<GrupoDTO> grupoList(){
        return grupoModelAssembler.toCollectionDTO(grupoRepository.findAll());
    }

    @GetMapping("/{grupoId}")
    public GrupoDTO grupoBuscar(@PathVariable("grupoId") Long id){
        return grupoModelAssembler.toModel(cadastroGrupo.buscarOuFalhar(id));
    }

    @PostMapping
    public GrupoDTO grupoAdd(@RequestBody @Valid GrupoInputDTO grupoInput){
        return grupoModelAssembler.toModel(
                cadastroGrupo.salvar(
                    grupoInputDisassembler.toDomainObject(grupoInput)));
    }

    @PutMapping("/{grupoId}")
    public GrupoDTO grupoUpdate(@PathVariable("grupoId") Long id, @RequestBody GrupoInputDTO grupoInput){
        Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(id);
        grupoInputDisassembler.copyToDomainObject(grupoInput,grupoAtual);
        return  grupoModelAssembler.toModel(
                cadastroGrupo.salvar(grupoAtual));
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void grupoDeletar(@PathVariable("grupoId") Long id){
        cadastroGrupo.excluir(id);
    }
}
