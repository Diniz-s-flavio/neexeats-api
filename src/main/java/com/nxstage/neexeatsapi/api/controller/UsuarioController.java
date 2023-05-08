package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.UsuarioInputDisassembler;
import com.nxstage.neexeatsapi.api.assembler.UsuarioModelAssembler;
import com.nxstage.neexeatsapi.api.dto.UsuarioDTO;
import com.nxstage.neexeatsapi.api.dto.input.UsuarioInputDTO;
import com.nxstage.neexeatsapi.api.dto.input.UsuarioSemSenhaInputDTO;
import com.nxstage.neexeatsapi.api.dto.input.UsuarioSenhaInputDTO;
import com.nxstage.neexeatsapi.domain.model.Usuario;
import com.nxstage.neexeatsapi.domain.repository.UsuarioRepository;
import com.nxstage.neexeatsapi.domain.service.CadastroUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{usuarioId}")
    public UsuarioDTO buscarUsuario(@PathVariable("usuarioId") Long id){
        return usuarioModelAssembler.toModel(
                cadastroUsuario.buscarOuFalhar(id));
    }

    @GetMapping
    public List<UsuarioDTO> listUsuario(){
        return usuarioModelAssembler.toCollectionDTO(
                usuarioRepository.findAll());
    }

    @PostMapping
    public UsuarioDTO addUsuario(@RequestBody @Valid UsuarioInputDTO usuarioInput){
        return usuarioModelAssembler.toModel(
                cadastroUsuario.salvar(
                        usuarioInputDisassembler.toDomainObject(usuarioInput)
                )
        );
    }

    @PutMapping("/{usuarioId}")
    public UsuarioDTO updateUsuario(@PathVariable("usuarioId") Long id,
                                    @RequestBody @Valid UsuarioSemSenhaInputDTO usuarioSemSenhaInput){
        Usuario usuario = cadastroUsuario.buscarOuFalhar(id);
        usuarioInputDisassembler.copyToDomainObject(usuarioSemSenhaInput,usuario);

        return usuarioModelAssembler.toModel(
                cadastroUsuario.salvar(usuario));
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenhaUsuario(@PathVariable("usuarioId") Long id,
                                          @RequestBody @Valid UsuarioSenhaInputDTO usuarioSenhaInput){
        cadastroUsuario.alterarSenha(id, usuarioSenhaInput.getSenha(), usuarioSenhaInput.getNovaSenha());
    }
}
