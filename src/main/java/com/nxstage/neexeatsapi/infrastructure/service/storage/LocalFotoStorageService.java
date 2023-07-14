package com.nxstage.neexeatsapi.infrastructure.service.storage;

import com.nxstage.neexeatsapi.domain.service.FotoStorageService;
import com.nxstage.neexeatsapi.infrastructure.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalFotoStorageService implements FotoStorageService {

    @Value("${nxstage.storage.local.photo-directory}")
    private Path photosDirectory;

    @Override
    public InputStream recover(String fileName) {
        try {
            Path filePath = getFilePath(fileName);

            return Files.newInputStream(filePath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar o arquivo.",e);
        }

    }

    @Override
    public void storage(NovaFoto newPhoto) {
        try {
            Path filePath = getFilePath(newPhoto.getNomeArquivo());

            FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(filePath));
        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar arquivo.",e);
        }
    }

    @Override
    public void remove(String fileName) {
        Path filePath = getFilePath(fileName);

        try {
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir arquivo.",e);
        }
    }

    private Path getFilePath(String fileName){
        return photosDirectory.resolve(Path.of(fileName));
    }
}
