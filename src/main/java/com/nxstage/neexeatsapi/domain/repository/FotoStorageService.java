package com.nxstage.neexeatsapi.domain.repository;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {
    void storage(NovaFoto newPhoto);

    default String generateFileName(String originName){
        return UUID.randomUUID().toString() + "_" + originName;
    }

    @Getter
    @Builder
    class NovaFoto {
        private String nomeArquivo;
        private InputStream inputStream;
    }
}
