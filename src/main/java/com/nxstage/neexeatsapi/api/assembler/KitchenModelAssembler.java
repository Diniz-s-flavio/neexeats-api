package com.nxstage.neexeatsapi.api.assembler;

import com.nxstage.neexeatsapi.api.dto.KitchenDTO;
import com.nxstage.neexeatsapi.domain.model.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KitchenModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public KitchenDTO toModel(Kitchen kitchen){
        return modelMapper.map(kitchen, KitchenDTO.class);
    }
    public List<KitchenDTO> toCollectionDTO(List<Kitchen> kitchens){
        return kitchens.stream().map(kitchen -> toModel(kitchen))
                .collect(Collectors.toList());
    }
}
