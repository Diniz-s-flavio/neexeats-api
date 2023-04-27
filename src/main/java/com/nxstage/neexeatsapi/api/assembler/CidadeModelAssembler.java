package com.nxstage.neexeatsapi.api.assembler;

import com.nxstage.neexeatsapi.api.dto.CidadeDTO;
import com.nxstage.neexeatsapi.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeModelAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public CidadeDTO toModel(Cidade cidade){
        return  modelMapper.map(cidade, CidadeDTO.class);
    }

    public List<CidadeDTO> toCollectionDTO(List<Cidade> cidades){
        return cidades.stream()
                .map(cidade -> toModel(cidade)).collect(Collectors.toList());
    }
}
