package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.exception.CidadeNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.exception.EntidadeEmUsoException;
import com.nxstage.neexeatsapi.domain.exception.PedidoNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.model.Cidade;
import com.nxstage.neexeatsapi.domain.model.Estado;
import com.nxstage.neexeatsapi.domain.model.Pedido;
import com.nxstage.neexeatsapi.domain.repository.CidadeRepository;
import com.nxstage.neexeatsapi.domain.repository.EstadoRepository;
import com.nxstage.neexeatsapi.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscarOuFalhar(Long pedidoId){
        return pedidoRepository.findById(pedidoId).orElseThrow(()->
                new PedidoNaoEncontradaException(pedidoId));
    }
    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }
    @Transactional
    public Pedido salvar(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void excluir(Long pedidoId){
        try {
            pedidoRepository.deleteById(pedidoId);
            pedidoRepository.flush();
        }catch(EmptyResultDataAccessException e) {
            throw new PedidoNaoEncontradaException(pedidoId);
        }
    }
}
