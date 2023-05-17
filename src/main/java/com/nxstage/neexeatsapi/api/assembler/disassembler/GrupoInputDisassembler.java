package com.nxstage.neexeatsapi.api.assembler.disassembler;

import com.nxstage.neexeatsapi.api.dto.input.GrupoInputDTO;
import com.nxstage.neexeatsapi.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoInputDisassembler {
    @Autowired
    private ModelMapper  modelMapper;

    public Grupo toDomainObject(GrupoInputDTO grupoInput){
        return modelMapper.map(grupoInput, Grupo.class);
    }

    public void copyToDomainObject(GrupoInputDTO grupoInput, Grupo grupo){
        modelMapper.map(grupoInput, grupo);
    }
}
