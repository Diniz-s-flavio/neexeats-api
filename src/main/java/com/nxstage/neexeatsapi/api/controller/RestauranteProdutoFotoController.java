package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.FotoProdutoModelAssembler;
import com.nxstage.neexeatsapi.api.dto.FotoProdutoDTO;
import com.nxstage.neexeatsapi.api.dto.input.FotoProdutoInputDTO;
import com.nxstage.neexeatsapi.domain.model.FotoProduto;
import com.nxstage.neexeatsapi.domain.model.Produto;
import com.nxstage.neexeatsapi.domain.service.CadastroProdutoService;
import com.nxstage.neexeatsapi.domain.service.CatalogoFotoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProduto;

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private FotoProdutoModelAssembler fotoProdutoModelAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoDTO updatePhoto(@PathVariable Long restauranteId,
                                      @PathVariable Long produtoId, @Valid FotoProdutoInputDTO fotoProdutoInputDTO) throws IOException {
        FotoProduto photo = new FotoProduto();
        Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
        MultipartFile file = fotoProdutoInputDTO.getArquivo();

        photo.setProduto(produto);
        photo.setDescricao(fotoProdutoInputDTO.getDescricao());
        photo.setContentType(file.getContentType());
        photo.setTamanho(file.getSize());
        photo.setNomeArquivo(file.getOriginalFilename());
        System.out.println("NomeArquivo no input: " + photo.getNomeArquivo());

        FotoProduto savedPhoto = catalogoFotoProduto.salvar(photo,file.getInputStream());

        return fotoProdutoModelAssembler.toModel(savedPhoto);
    }
}
