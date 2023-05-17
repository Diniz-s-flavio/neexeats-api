package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.GrupoModelAssembler;
import com.nxstage.neexeatsapi.api.assembler.PermissaoModelAssembler;
import com.nxstage.neexeatsapi.api.assembler.disassembler.GrupoInputDisassembler;
import com.nxstage.neexeatsapi.api.dto.GrupoDTO;
import com.nxstage.neexeatsapi.api.dto.PermissaoDTO;
import com.nxstage.neexeatsapi.api.dto.input.GrupoInputDTO;
import com.nxstage.neexeatsapi.domain.model.Grupo;
import com.nxstage.neexeatsapi.domain.model.Permissao;
import com.nxstage.neexeatsapi.domain.repository.GrupoRepository;
import com.nxstage.neexeatsapi.domain.service.CadastroGrupoService;
import com.nxstage.neexeatsapi.domain.service.CadastroPermissaoService;
import com.nxstage.neexeatsapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {
    @Autowired
    private CadastroGrupoService cadastroGrupo;
    @Autowired
    private CadastroPermissaoService cadastroPermissao;
    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @GetMapping
    public Set<PermissaoDTO> grupoList(@PathVariable("grupoId") Long grupoId){
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
        return permissaoModelAssembler.toCollectionDTO(grupo.getPermissoes());
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void linkPermissao(@PathVariable("grupoId") Long grupoId, @PathVariable("permissaoId") Long permissaoId){
        cadastroGrupo.linkPermissao(grupoId,permissaoId);
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unlinkPermissao(@PathVariable("grupoId") Long grupoId, @PathVariable("permissaoId") Long permissaoId){
        cadastroGrupo.unlinkPermissao(grupoId,permissaoId);
    }
}
