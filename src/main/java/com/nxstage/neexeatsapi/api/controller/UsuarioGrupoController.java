package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.GrupoModelAssembler;
import com.nxstage.neexeatsapi.api.assembler.UsuarioModelAssembler;
import com.nxstage.neexeatsapi.api.assembler.disassembler.UsuarioInputDisassembler;
import com.nxstage.neexeatsapi.api.dto.GrupoDTO;
import com.nxstage.neexeatsapi.api.dto.UsuarioDTO;
import com.nxstage.neexeatsapi.api.dto.input.UsuarioInputDTO;
import com.nxstage.neexeatsapi.api.dto.input.UsuarioSemSenhaInputDTO;
import com.nxstage.neexeatsapi.api.dto.input.UsuarioSenhaInputDTO;
import com.nxstage.neexeatsapi.domain.model.Usuario;
import com.nxstage.neexeatsapi.domain.repository.GrupoRepository;
import com.nxstage.neexeatsapi.domain.repository.UsuarioRepository;
import com.nxstage.neexeatsapi.domain.service.CadastroUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {
    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private CadastroUsuarioService cadastroUsuario;
    @Autowired
    private GrupoModelAssembler grupoModelAssembler;

    @GetMapping
    public List<GrupoDTO> listGrupos(@PathVariable("usuarioId") Long usuarioId){
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
        return grupoModelAssembler.toCollectionDTO(
                usuario.getGrupos());
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void linkGrupo(@PathVariable("usuarioId") Long usuarioId,
                                    @PathVariable("grupoId") Long grupoId){

        cadastroUsuario.linkGrupo(usuarioId,grupoId);
    }
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unlinkGrupo(@PathVariable("usuarioId") Long usuarioId,
                                    @PathVariable("grupoId") Long grupoId){

        cadastroUsuario.unlinkGrupo(usuarioId,grupoId);
    }
}
