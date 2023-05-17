package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.exception.EntidadeEmUsoException;
import com.nxstage.neexeatsapi.domain.exception.EntidadeNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.exception.GrupoNaoEncontradoException;
import com.nxstage.neexeatsapi.domain.model.Grupo;
import com.nxstage.neexeatsapi.domain.model.Permissao;
import com.nxstage.neexeatsapi.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CadastroGrupoService {

    public static final String MSG_GRUPO_NAO_EMCONTRADO = "O Grupo de id '%d' não encontrada";
    public static final String MSG_ENTIDADE_EM_USO = "Não foi possível excluir Grupo de id '%d', Pois esta em uso";
    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private CadastroPermissaoService cadastroPermissao;

    public Grupo buscarOuFalhar(Long id){
        return grupoRepository.findById(id).orElseThrow(()->
                new GrupoNaoEncontradoException(id));
    }

    @Transactional
    public Grupo salvar(Grupo grupo){
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void excluir(Long id){
        try {
            grupoRepository.deleteById(id);
            grupoRepository.flush();
        }catch (EmptyResultDataAccessException e){
            throw new GrupoNaoEncontradoException(
                    String.format(MSG_GRUPO_NAO_EMCONTRADO,id));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format(MSG_ENTIDADE_EM_USO,id));
        }
    }

    @Transactional
    public void linkPermissao(Long grupoId, Long permissaoId){
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);

        grupo.linkPermissao(permissao);
    }

    @Transactional
    public void unlinkPermissao(Long grupoId, Long permissaoId){
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);

        grupo.unlinkPermissao(permissao);
    }
}
