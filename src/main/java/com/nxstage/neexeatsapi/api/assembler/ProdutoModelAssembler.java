package com.nxstage.neexeatsapi.api.assembler;


import com.nxstage.neexeatsapi.api.dto.ProdutoDTO;
import com.nxstage.neexeatsapi.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    public ProdutoDTO toModel(Produto produto){
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public List<ProdutoDTO> toCollectionDTO(List<Produto> produtos){
        return produtos.stream().map(produto -> toModel(produto)).collect(Collectors.toList());
    }
}
