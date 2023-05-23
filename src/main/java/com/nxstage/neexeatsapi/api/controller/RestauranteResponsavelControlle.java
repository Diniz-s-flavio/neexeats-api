package com.nxstage.neexeatsapi.api.controller;

import com.nxstage.neexeatsapi.api.assembler.UsuarioModelAssembler;
import com.nxstage.neexeatsapi.api.dto.ProdutoDTO;
import com.nxstage.neexeatsapi.api.dto.UsuarioDTO;
import com.nxstage.neexeatsapi.api.dto.input.ProdutoInputDTO;
import com.nxstage.neexeatsapi.domain.model.Produto;
import com.nxstage.neexeatsapi.domain.model.Restaurante;
import com.nxstage.neexeatsapi.domain.model.Usuario;
import com.nxstage.neexeatsapi.domain.service.CadastroRestauranteService;
import com.nxstage.neexeatsapi.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
public class RestauranteResponsavelControlle {
    @Autowired
    private CadastroRestauranteService cadastroRestaurante;
    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;
    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @GetMapping
    public List<UsuarioDTO> listResponsaveis(@PathVariable("restauranteId") Long restauranteId){
        Restaurante restaurante = cadastroRestaurante.buscaOuFalhar(restauranteId);

        return usuarioModelAssembler.toCollectionDTO(
                cadastroUsuario.findByRestaurantes(restaurante));
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void linkUsuarioRestaurante(@PathVariable("restauranteId") Long restauranteId,
                                    @PathVariable("usuarioId") Long usuarioId){

        cadastroRestaurante.linkUsuarioResponsavel(restauranteId,usuarioId);
    }
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unlinkUsuarioRestaurante(@PathVariable("restauranteId") Long restauranteId,
                                    @PathVariable("usuarioId") Long usuarioId){

        cadastroRestaurante.unlinkUsuarioResponsavel(restauranteId,usuarioId);
    }
}
