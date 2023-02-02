package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.domain.exception.EntidadeEmUsoException;
import com.nxstage.neexeatsapi.domain.exception.EntidadeNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.model.Estado;
import com.nxstage.neexeatsapi.domain.repository.EstadoRepository;
import com.nxstage.neexeatsapi.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    @GetMapping
    public List<Estado> estadoList(){
        return estadoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado estadoAdd(@RequestBody Estado estado){
        return cadastroEstado.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    public Estado estadoUpdate(
            @PathVariable("estadoId")Long id,@RequestBody Estado estado){
        Estado updatingEstado = cadastroEstado.buscarOuFalhar(id);

            BeanUtils.copyProperties(estado,updatingEstado,"id");
            return cadastroEstado.salvar(updatingEstado);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEstado(@PathVariable Long estadoId){
            cadastroEstado.excluir(estadoId);
    }
}
