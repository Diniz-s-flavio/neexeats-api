package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.disassembler.EstadoInputDisassembler;
import com.nxstage.neexeatsapi.api.assembler.EstadoModelAssembler;
import com.nxstage.neexeatsapi.api.dto.EstadoDTO;
import com.nxstage.neexeatsapi.api.dto.input.EstadoInputDTO;
import com.nxstage.neexeatsapi.domain.model.Estado;
import com.nxstage.neexeatsapi.domain.repository.EstadoRepository;
import com.nxstage.neexeatsapi.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @GetMapping
    public List<EstadoDTO> estadoList(){
        return estadoModelAssembler.toCollectionDTO(
                estadoRepository.findAll());
    }

    @GetMapping("/{id}")
    public EstadoDTO estadoOne(@PathVariable Long id){
        return estadoModelAssembler.toModel(
                cadastroEstado.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDTO estadoAdd(@RequestBody @Valid EstadoInputDTO estadoInput){
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
        return estadoModelAssembler.toModel(
                cadastroEstado.salvar(estado));
    }

    @PutMapping("/{estadoId}")
    public EstadoDTO estadoUpdate(
            @PathVariable("estadoId")Long id,@RequestBody @Valid EstadoInputDTO estadoInput){
        Estado updatingEstado = cadastroEstado.buscarOuFalhar(id);

            estadoInputDisassembler.copyToDomainObject(estadoInput,updatingEstado);
            return estadoModelAssembler.toModel(
                    cadastroEstado.salvar(updatingEstado));
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEstado(@PathVariable Long estadoId){
            cadastroEstado.excluir(estadoId);
    }
}
