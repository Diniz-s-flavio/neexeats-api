package com.nxstage.neexeatsapi.api.assembler.disassembler;

import com.nxstage.neexeatsapi.api.dto.input.RestauranteInputDTO;
import com.nxstage.neexeatsapi.domain.model.Cidade;
import com.nxstage.neexeatsapi.domain.model.Endereco;
import com.nxstage.neexeatsapi.domain.model.Kitchen;
import com.nxstage.neexeatsapi.domain.model.Restaurante;
import com.nxstage.neexeatsapi.domain.service.CadastroCidadeService;
import com.nxstage.neexeatsapi.domain.service.CadastroCozinhaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {
    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInputDTO restauranteInput){
        return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteInputDTO restauranteInput, Restaurante restaurante){
        //Para evitar Exception:
        // identifier of an instance of com.nxstage.neexeatsapi.domain.model.Kitchen was altered from 2 to 1
        restaurante.setKitchen(new Kitchen());

        if(restaurante.getEndereco() !=null){
            restaurante.getEndereco().setCidade(new Cidade());
        }

        modelMapper.map(restauranteInput,restaurante);
    }
}
