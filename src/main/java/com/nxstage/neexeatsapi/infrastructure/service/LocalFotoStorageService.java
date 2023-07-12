package com.nxstage.neexeatsapi.infrastructure.service;

import com.nxstage.neexeatsapi.domain.repository.FotoStorageService;
import com.nxstage.neexeatsapi.infrastructure.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalFotoStorageService implements FotoStorageService {

    @Value("${nxstage.storage.local.photo-directory}")
    private Path photosDirectory;

    @Override
    public void storage(NovaFoto newPhoto) {
        try {
            Path filePath = getFilePath(newPhoto.getNomeArquivo());

            FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(filePath));
        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar arquivo.",e);
        }
    }

    private Path getFilePath(String fileName){
        return photosDirectory.resolve(Path.of(fileName));
    }
}
