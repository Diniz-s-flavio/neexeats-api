package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.dto.input.FotoProdutoInputDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updatePhoto(@PathVariable Long restauranteId,
                            @PathVariable Long produtoId, @Valid FotoProdutoInputDTO fotoProdutoInputDTO){
        String filePath = "C:\\Users\\ajayf\\Desktop\\Dotz\\Alpha\\03 - Estudos\\Estudo Java\\SpringBoot\\neexeats-api\\src\\main\\resources\\testes-img";

        var fileName = UUID.randomUUID().toString()
                + "_" + fotoProdutoInputDTO.getArquivo().getOriginalFilename();

        var filePhoto = Path.of(filePath,fileName);

        System.out.println(fotoProdutoInputDTO.getDescricao());
        System.out.println(filePhoto);
        System.out.println(fotoProdutoInputDTO.getArquivo().getContentType());

        try {
            fotoProdutoInputDTO.getArquivo().transferTo(filePhoto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
