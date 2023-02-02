package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.domain.exception.EstadoNaoEncontradoException;
import com.nxstage.neexeatsapi.domain.exception.NegocioException;
import com.nxstage.neexeatsapi.domain.model.Cidade;
import com.nxstage.neexeatsapi.domain.repository.CidadeRepository;
import com.nxstage.neexeatsapi.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @GetMapping
    public ResponseEntity<List> cidadeList(){
        return ResponseEntity.ok().body(cidadeRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade cidadeAdd(@RequestBody Cidade cidade){
        try{
            return cadastroCidade.salvar(cidade);
        }catch(EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @PutMapping("/{cidadeId}")
    public Cidade cidadeUpdate(@PathVariable("cidadeId") Long id, @RequestBody Cidade cidade){
        try{Cidade updatingCidade = cadastroCidade.buscarOuFalhar(id);
            BeanUtils.copyProperties(cidade,updatingCidade,"id");

            return cadastroCidade.salvar(updatingCidade);
        }catch(EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cidadeExcluir(@PathVariable("cidadeId")Long id){
            cadastroCidade.excluir(id);
    }
}
