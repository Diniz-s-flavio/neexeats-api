package com.nxstage.neexeatsapi.api.assembler;

import com.nxstage.neexeatsapi.api.dto.input.FormaPagInputDTO;
import com.nxstage.neexeatsapi.domain.model.FormaPag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    public FormaPag toDomainObject(FormaPagInputDTO formaPagInput){
        return modelMapper.map(formaPagInput,FormaPag.class);
    }

    public void copyToDomainObject(FormaPagInputDTO formaPagInput, FormaPag formaPag){
        modelMapper.map(formaPagInput,formaPag);
    }
}
