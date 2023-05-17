package com.nxstage.neexeatsapi.api.assembler.disassembler;

import com.nxstage.neexeatsapi.api.dto.UsuarioDTO;
import com.nxstage.neexeatsapi.api.dto.input.UsuarioInputDTO;
import com.nxstage.neexeatsapi.api.dto.input.UsuarioSemSenhaInputDTO;
import com.nxstage.neexeatsapi.api.dto.input.UsuarioSenhaInputDTO;
import com.nxstage.neexeatsapi.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioInputDTO usuarioInput){
        return modelMapper.map(usuarioInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioSemSenhaInputDTO usuarioInput, Usuario usuario){
        modelMapper.map(usuarioInput,usuario);
    }
}
