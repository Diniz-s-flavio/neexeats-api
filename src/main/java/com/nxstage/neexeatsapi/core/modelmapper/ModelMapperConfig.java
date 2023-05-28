package com.nxstage.neexeatsapi.core.modelmapper;

import com.nxstage.neexeatsapi.api.dto.EnderecoDTO;
import com.nxstage.neexeatsapi.api.dto.input.ItemPedidoInputDTO;
import com.nxstage.neexeatsapi.domain.model.Endereco;
import com.nxstage.neexeatsapi.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(ItemPedidoInputDTO.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        var enderecoToEnderecoDTOTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);

        enderecoToEnderecoDTOTypeMap.<String>addMapping(
                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
                (enderecoDTODest, value) -> enderecoDTODest.getCidade().setEstado(value));
        return modelMapper;
    }
}
