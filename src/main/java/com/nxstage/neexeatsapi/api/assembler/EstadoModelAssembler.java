package com.nxstage.neexeatsapi.api.assembler;

import com.nxstage.neexeatsapi.api.dto.EstadoDTO;
import com.nxstage.neexeatsapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoModelAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public EstadoDTO toModel(Estado estado){
        return modelMapper.map(estado, EstadoDTO.class);
    }

    public List<EstadoDTO> toCollectionDTO(List<Estado> estados){
        return estados.stream()
                .map(estado -> toModel(estado)).collect(Collectors.toList());
    }

}
