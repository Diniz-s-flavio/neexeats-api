package com.nxstage.neexeatsapi.domain.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.Map;
import java.util.Set;

public interface EnvioEmailService {

    void send(Mensagem message);

    @Getter
    @Builder
    class Mensagem{
        @Singular
        private Set<String> destinatarios;
        @NonNull
        private String assunto;
        @NonNull
        private String corpo;
        @Singular("variavel")
        private Map<String, Object> variaveis;
    }
}
