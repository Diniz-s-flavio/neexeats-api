package com.nxstage.neexeatsapi.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {
    InputStream recover(String fileName);
    void storage(NovaFoto newPhoto);
    void remove(String fileName);
    default void replace(String oldFileName, NovaFoto newPhoto){
        this.storage(newPhoto);
        if (oldFileName != null) {
            this.remove(oldFileName);
        }
    }

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
