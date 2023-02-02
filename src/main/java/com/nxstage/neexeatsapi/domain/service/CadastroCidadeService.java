package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.exception.CidadeNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.exception.EntidadeEmUsoException;
import com.nxstage.neexeatsapi.domain.exception.EntidadeNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.model.Cidade;
import com.nxstage.neexeatsapi.domain.model.Estado;
import com.nxstage.neexeatsapi.domain.repository.CidadeRepository;
import com.nxstage.neexeatsapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCidadeService {

    public static final String MSG_CIDADE_EM_USO = "A Cidade de Id: %d, não pode ser removida pois esta em uso";
    @Autowired
    private CadastroEstadoService cadastroEstado;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salvar(Cidade cidade){
        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public void excluir(Long cidadeId){
        try {
            cidadeRepository.deleteById(cidadeId);
        }catch(EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(cidadeId);
        }catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_EM_USO,cidadeId));
        }
    }

    public Cidade buscarOuFalhar(Long cidadeId){
        return cidadeRepository.findById(cidadeId).orElseThrow(()->
                new CidadeNaoEncontradaException(cidadeId));
    }
}
