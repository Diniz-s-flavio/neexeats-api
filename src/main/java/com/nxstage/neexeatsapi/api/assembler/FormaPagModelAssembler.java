package com.nxstage.neexeatsapi.api.assembler;

import com.nxstage.neexeatsapi.api.dto.FormaPagDTO;
import com.nxstage.neexeatsapi.domain.model.FormaPag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagModelAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public FormaPagDTO toModel(FormaPag formaPag){
        return modelMapper.map(formaPag, FormaPagDTO.class);
    }

    public List<FormaPagDTO> toCollectionDTO(List<FormaPag> formaPags){
        return formaPags.stream().map(formaPag -> toModel(formaPag))
                .collect(Collectors.toList());
    }
}
