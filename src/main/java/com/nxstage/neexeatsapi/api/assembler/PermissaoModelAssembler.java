package com.nxstage.neexeatsapi.api.assembler;

import com.nxstage.neexeatsapi.api.dto.EstadoDTO;
import com.nxstage.neexeatsapi.api.dto.PermissaoDTO;
import com.nxstage.neexeatsapi.domain.model.Estado;
import com.nxstage.neexeatsapi.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PermissaoModelAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public PermissaoDTO toModel(Permissao permissao){
        return modelMapper.map(permissao, PermissaoDTO.class);
    }

    public Set<PermissaoDTO> toCollectionDTO(Set<Permissao> permissoes){
        return permissoes.stream()
                .map(permissao -> toModel(permissao)).collect(Collectors.toSet());
    }

}
