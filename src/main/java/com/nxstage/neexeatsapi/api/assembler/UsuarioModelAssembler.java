package com.nxstage.neexeatsapi.api.assembler;

import com.nxstage.neexeatsapi.api.dto.UsuarioDTO;
import com.nxstage.neexeatsapi.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDTO toModel(Usuario usuario){
        return modelMapper.map(usuario,UsuarioDTO.class);
    }

    public List<UsuarioDTO> toCollectionDTO(List<Usuario> usuarios){
        return usuarios.stream().map(usuario -> toModel(usuario)).collect(Collectors.toList());
    }
}
