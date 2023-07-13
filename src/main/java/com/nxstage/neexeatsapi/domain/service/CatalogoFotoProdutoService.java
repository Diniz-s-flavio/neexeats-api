package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.model.FotoProduto;
import com.nxstage.neexeatsapi.domain.repository.FotoStorageService;
import com.nxstage.neexeatsapi.domain.repository.FotoStorageService.NovaFoto;
import com.nxstage.neexeatsapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorage;

    @Transactional
    public FotoProduto salvar(FotoProduto photo, InputStream fileData){
        Long restauranteId = photo.getRestauranteId();
        Long produtoId = photo.getProduto().getId();
        String nameNewFile = fotoStorage.generateFileName(photo.getNomeArquivo());

        Optional<FotoProduto>  existingPhoto = produtoRepository.findPhotoById(restauranteId,produtoId);
        String oldFileName = null;

        if (existingPhoto.isPresent()){
            produtoRepository.delete(existingPhoto.get());
            oldFileName = existingPhoto.get().getNomeArquivo();
        }

        photo.setNomeArquivo(nameNewFile);
        photo = produtoRepository.save(photo);
        produtoRepository.flush();

        NovaFoto newPhoto = NovaFoto.builder()
                .nomeArquivo(photo.getNomeArquivo())
                .inputStream(fileData)
                .build();

        fotoStorage.replace(oldFileName, newPhoto);

        return photo;
    }
}
