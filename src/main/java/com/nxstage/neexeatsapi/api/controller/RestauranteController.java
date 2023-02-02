package com.nxstage.neexeatsapi.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nxstage.neexeatsapi.domain.exception.CozinhaNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.exception.EntidadeEmUsoException;
import com.nxstage.neexeatsapi.domain.exception.EntidadeNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.exception.NegocioException;
import com.nxstage.neexeatsapi.domain.model.Restaurante;
import com.nxstage.neexeatsapi.domain.repository.RestauranteRepository;
import com.nxstage.neexeatsapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @GetMapping
    public List<Restaurante> restauranteList(){
        return restauranteRepository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public Restaurante buscar(@PathVariable("restauranteId") Long id){
        return cadastroRestaurante.buscaOuFalhar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante restauranteAdd(@RequestBody Restaurante restaurante){
        try{
            return cadastroRestaurante.salvar(restaurante);
        }catch(CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    @PutMapping("/{restauranteId}")
    public Restaurante restauranteUpdate(
            @PathVariable("restauranteId") Long id, @RequestBody Restaurante restaurante) {
            Restaurante updatingRestaurante = cadastroRestaurante.buscaOuFalhar(id);

            BeanUtils.copyProperties(restaurante, updatingRestaurante, "id", "formasPag","endereco",
            "dataCadastro");

        try{
            return cadastroRestaurante.salvar(updatingRestaurante);
        }catch(CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage(),e);
        }
    }

    /*@DeleteMapping("/{restauranteId}")
    public ResponseEntity restauranteExcluir(@PathVariable("restauranteId") Long restauranteId){
        Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
        if(restaurante.isPresent()){
            try{cadastroRestaurante.excluir(restauranteId);
            return ResponseEntity.noContent().build();
            }catch(EntidadeEmUsoException e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }else return ResponseEntity.notFound().build();
    }*/

    @PatchMapping("/{restauranteId}")
    public Restaurante parcUpRestaurante(@PathVariable("restauranteId")Long id, @RequestBody Map<String,Object> campos){
        Restaurante restauranteAtual = cadastroRestaurante.buscaOuFalhar(id);

        merge(campos,restauranteAtual);
        return restauranteUpdate(id,restauranteAtual);
    }

    private static void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigen = objectMapper.convertValue(dadosOrigem, Restaurante.class);
        dadosOrigem.forEach((nomePropriedade, valorPropriedade) ->{
            Field field = ReflectionUtils.findField(Restaurante.class,nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigen);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

}
