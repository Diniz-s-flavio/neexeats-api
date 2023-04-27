package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.exception.CozinhaNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.exception.EntidadeEmUsoException;
import com.nxstage.neexeatsapi.domain.exception.EntidadeNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.model.Kitchen;
import com.nxstage.neexeatsapi.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CadastroCozinhaService {
    public static final String MSG_COZINHA_NAO_ENCONTRADA
            = "Cozinha de código %d não Existe";
    public static final String MSG_COZINHA_EM_USO
            = "Cozinha de código %d não pode ser removida, pois está em uso";
    @Autowired
    private KitchenRepository kitchenRepository;

    @Transactional
    public Kitchen salvar( Kitchen kitchen){
        return kitchenRepository.save(kitchen);
    }

    @Transactional
    public void excluir(Long kitchenId){
        try {
            kitchenRepository.deleteById(kitchenId);
            kitchenRepository.flush();
        }catch(EmptyResultDataAccessException e){
            throw new CozinhaNaoEncontradaException(kitchenId);
        }catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format(MSG_COZINHA_EM_USO,kitchenId));
        }
    }

    public  Kitchen buscarOuFalhar(Long kitchenId){
        return kitchenRepository.findById(kitchenId)
                .orElseThrow(()-> new CozinhaNaoEncontradaException(kitchenId));
    }

}
