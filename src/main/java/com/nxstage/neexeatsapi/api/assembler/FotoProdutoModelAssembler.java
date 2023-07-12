package com.nxstage.neexeatsapi.api.assembler;

import com.nxstage.neexeatsapi.api.dto.FotoProdutoDTO;
import com.nxstage.neexeatsapi.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoModelAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoDTO toModel(FotoProduto photo){
        return modelMapper.map(photo, FotoProdutoDTO.class);
    }


}
