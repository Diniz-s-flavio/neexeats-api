package com.nxstage.neexeatsapi.api.assembler;

import com.nxstage.neexeatsapi.api.dto.input.KitchenInputDTO;
import com.nxstage.neexeatsapi.domain.model.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KitchenInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;
    public Kitchen toDomainObject(KitchenInputDTO kitchenInput){
        return modelMapper.map(kitchenInput,Kitchen.class);
    }
    public void copyToDomainObject(KitchenInputDTO kitchenInput,Kitchen kitchen){
        modelMapper.map(kitchenInput,kitchen);
    }
}
