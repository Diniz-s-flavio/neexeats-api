package com.nxstage.neexeatsapi.api.assembler.disassembler;

import com.nxstage.neexeatsapi.api.dto.ProdutoDTO;
import com.nxstage.neexeatsapi.api.dto.input.ProdutoInputDTO;
import com.nxstage.neexeatsapi.domain.model.Produto;
import com.nxstage.neexeatsapi.domain.model.Restaurante;
import com.nxstage.neexeatsapi.domain.service.CadastroProdutoService;
import com.nxstage.neexeatsapi.domain.service.CadastroRestauranteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainObject(ProdutoInputDTO produtoInput){
        return modelMapper.map(produtoInput, Produto.class);
    }

    public void copyToDomainObject(ProdutoInputDTO produtoInput, Produto produto){
        modelMapper.map(produtoInput, produto);
    }
}
