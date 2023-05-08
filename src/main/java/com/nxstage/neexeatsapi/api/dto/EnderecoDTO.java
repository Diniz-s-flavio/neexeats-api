package com.nxstage.neexeatsapi.api.dto;

import com.nxstage.neexeatsapi.api.dto.CidadeDTO;
import com.nxstage.neexeatsapi.api.dto.CidadeResumoDTO;
import lombok.Data;

@Data
public class EnderecoDTO {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeResumoDTO cidade;
}
