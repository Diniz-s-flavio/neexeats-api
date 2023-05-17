package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.disassembler.CidadeInputDisassembler;
import com.nxstage.neexeatsapi.api.assembler.CidadeModelAssembler;
import com.nxstage.neexeatsapi.api.dto.CidadeDTO;
import com.nxstage.neexeatsapi.api.dto.input.CidadeInputDTO;
import com.nxstage.neexeatsapi.domain.exception.EstadoNaoEncontradoException;
import com.nxstage.neexeatsapi.domain.exception.NegocioException;
import com.nxstage.neexeatsapi.domain.model.Cidade;
import com.nxstage.neexeatsapi.domain.repository.CidadeRepository;
import com.nxstage.neexeatsapi.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @GetMapping
    public List<CidadeDTO> cidadeList(){
        return cidadeModelAssembler.toCollectionDTO(
                cidadeRepository.findAll());
    }
    @GetMapping("/{id}")
    public CidadeDTO cidadeFind(@PathVariable Long id){
        return cidadeModelAssembler.toModel(
                cadastroCidade.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO cidadeAdd(@RequestBody @Valid CidadeInputDTO cidadeInput){
        Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
        try{
            return cidadeModelAssembler.toModel(
                    cadastroCidade.salvar(cidade));
        }catch(EstadoNaoEncontradoException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeDTO cidadeUpdate(@PathVariable("cidadeId") Long id, @RequestBody @Valid CidadeInputDTO cidadeInput){
        try{Cidade updatingCidade = cadastroCidade.buscarOuFalhar(id);
            cidadeInputDisassembler.copyToDomainObject(cidadeInput, updatingCidade);

            return cidadeModelAssembler.toModel(
                    cadastroCidade.salvar(updatingCidade));
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
