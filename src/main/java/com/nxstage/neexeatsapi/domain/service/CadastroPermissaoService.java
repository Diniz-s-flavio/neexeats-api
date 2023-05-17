package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.exception.PermissaoNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.model.Permissao;
import com.nxstage.neexeatsapi.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroPermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long id){
        return permissaoRepository.findById(id).orElseThrow(()->
                new PermissaoNaoEncontradaException(id));
    }

    @Transactional
    public Permissao salvar(Permissao permissao){
        return permissaoRepository.save(permissao);
    }
}
