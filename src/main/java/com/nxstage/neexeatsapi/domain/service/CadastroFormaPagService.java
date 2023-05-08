package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.exception.EntidadeEmUsoException;
import com.nxstage.neexeatsapi.domain.exception.FormaPagNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.model.FormaPag;
import com.nxstage.neexeatsapi.domain.repository.FormaPagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroFormaPagService {

    public static final String MSG_FORMA_PAG_EM_USO =
            "A forma de Pagamento de id '%d' não pode ser excluida, pois esta em uso";
    @Autowired
    private FormaPagRepository formaPagRepository;

    @Transactional
    public FormaPag salvar(FormaPag formaPag){
        return formaPagRepository.save(formaPag);
    }

    public FormaPag buscarOuFalhar(Long formaPagId){
        return formaPagRepository.findById(formaPagId).orElseThrow(()->
                new FormaPagNaoEncontradaException(formaPagId));
    }

    @Transactional
    public void excluir(Long formaPagId){
        try {
            formaPagRepository.deleteById(formaPagId);
            formaPagRepository.flush();
        }catch (EmptyResultDataAccessException e){
            throw new FormaPagNaoEncontradaException(formaPagId);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format(MSG_FORMA_PAG_EM_USO,formaPagId));
        }

    }
}
