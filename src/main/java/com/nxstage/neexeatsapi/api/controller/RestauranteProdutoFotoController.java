package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.FotoProdutoModelAssembler;
import com.nxstage.neexeatsapi.api.dto.FotoProdutoDTO;
import com.nxstage.neexeatsapi.api.dto.input.FotoProdutoInputDTO;
import com.nxstage.neexeatsapi.domain.exception.EntidadeNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.model.FotoProduto;
import com.nxstage.neexeatsapi.domain.model.Produto;
import com.nxstage.neexeatsapi.domain.service.CadastroProdutoService;
import com.nxstage.neexeatsapi.domain.service.CatalogoFotoProdutoService;
import com.nxstage.neexeatsapi.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProduto;

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @Autowired
    private FotoProdutoModelAssembler fotoProdutoModelAssembler;

    @Autowired
    private FotoStorageService fotoStorage;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoDTO findByProdutoId(@PathVariable Long restauranteId,
                                          @PathVariable Long produtoId){
        FotoProduto foundPhoto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);

        return  fotoProdutoModelAssembler.toModel(foundPhoto);
    }

    @GetMapping
    public ResponseEntity<InputStreamResource> servePhoto(@PathVariable Long restauranteId,
                                                          @PathVariable Long produtoId,
                                                          @RequestHeader(name = "accept") String acceptHeader)
            throws HttpMediaTypeNotAcceptableException {
        try {
            FotoProduto foundPhoto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);


            MediaType photoMediaType = MediaType.parseMediaType(foundPhoto.getContentType());
            List<MediaType> acceptMediaTypes = MediaType.parseMediaTypes(acceptHeader);

            verifyMediaTypeCompatibility(photoMediaType,acceptMediaTypes);

            InputStream inputStream = fotoStorage.recover(foundPhoto.getNomeArquivo());

            return ResponseEntity.ok()
                    .contentType(photoMediaType)
                    .body(new InputStreamResource(inputStream));
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }

    }

    private void verifyMediaTypeCompatibility(MediaType photoMediaType,
                                              List<MediaType> acceptMediaTypes) throws HttpMediaTypeNotAcceptableException {
        boolean compatible = acceptMediaTypes.stream()
                .anyMatch(acceptMediaType -> acceptMediaType.isCompatibleWith(photoMediaType));

        if (!compatible){
            throw new HttpMediaTypeNotAcceptableException(acceptMediaTypes);
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePhoto(@PathVariable Long restauranteId,
                            @PathVariable Long produtoId){
        FotoProduto foundPhoto = catalogoFotoProduto.buscarOuFalhar(restauranteId, produtoId);

        fotoStorage.remove(foundPhoto.getNomeArquivo());

        catalogoFotoProduto.delete(foundPhoto);
    }
}
