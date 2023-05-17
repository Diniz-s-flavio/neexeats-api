package com.nxstage.neexeatsapi.api.assembler.disassembler;

import com.nxstage.neexeatsapi.api.dto.input.CidadeInputDTO;
import com.nxstage.neexeatsapi.domain.model.Cidade;
import com.nxstage.neexeatsapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeInputDTO cidadeInput){
        return modelMapper.map(cidadeInput, Cidade.class);
    }

    public void copyToDomainObject(CidadeInputDTO cidadeInput, Cidade cidade){
        cidade.setEstado(new Estado());

        modelMapper.map(cidadeInput, cidade);
    }
}
