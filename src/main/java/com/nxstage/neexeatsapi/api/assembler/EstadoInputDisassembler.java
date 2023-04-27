package com.nxstage.neexeatsapi.api.assembler;

import com.nxstage.neexeatsapi.api.dto.EstadoDTO;
import com.nxstage.neexeatsapi.api.dto.input.EstadoInputDTO;
import com.nxstage.neexeatsapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public Estado toDomainObject(EstadoInputDTO estadoInput){
        return modelMapper.map(estadoInput, Estado.class);
    }

    public void copyToDomainObject(EstadoInputDTO estadoInput,Estado estado){
        modelMapper.map(estadoInput,estado);
    }
}
