package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.exception.RestauranteNaoEncontradoException;
import com.nxstage.neexeatsapi.domain.model.Cidade;
import com.nxstage.neexeatsapi.domain.model.FormaPag;
import com.nxstage.neexeatsapi.domain.model.Kitchen;
import com.nxstage.neexeatsapi.domain.model.Restaurante;
import com.nxstage.neexeatsapi.domain.repository.KitchenRepository;
import com.nxstage.neexeatsapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {

    public static final String MSG_RESTAURANTE_NAO_ENCONTRADO
            = "O Restaurante de Id: %d não consta na base de dados";
    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private KitchenRepository kitchenRepository;
    @Autowired
    private CadastroFormaPagService cadastroFormaPagService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante){
        Long kitchenId = restaurante.getKitchen().getId();
        Long cidadeId = restaurante.getEndereco().getCidade().getId();
        Kitchen kitchen = cadastroCozinha.buscarOuFalhar(kitchenId);
        Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
        restaurante.setKitchen(kitchen);
        restaurante.getEndereco().setCidade(cidade);

        return    restauranteRepository.save(restaurante);
    }

    public Restaurante buscaOuFalhar(Long restauranteId){
        return restauranteRepository.findById(restauranteId).orElseThrow(()->
                new RestauranteNaoEncontradoException(restauranteId));
    }

    @Transactional
    public void ativar(Long restauranteId){
        Restaurante restaurante = buscaOuFalhar(restauranteId);
        restaurante.ativar();
    }

    @Transactional
    public void inativar(Long restauranteId){
        Restaurante restaurante = buscaOuFalhar(restauranteId);
        restaurante.inativar();
    }

    @Transactional
    public void desassociarFormasPag(Long restauranteId, Long formaPagId){
        Restaurante restaurante = buscaOuFalhar(restauranteId);
        FormaPag formaPag = cadastroFormaPagService.buscarOuFalhar(formaPagId);

        restaurante.removerFormaPag(formaPag);
    }

    @Transactional
    public void associarFormaPag(Long restauranteId, Long formaPagId){
        Restaurante restaurante = buscaOuFalhar(restauranteId);
        FormaPag formaPag = cadastroFormaPagService.buscarOuFalhar(formaPagId);

        restaurante.adicionarFormaPag(formaPag);
    }

    @Transactional
    public void openRestaurante(Long id){
        Restaurante restaurante = buscaOuFalhar(id);
        restaurante.open();
    }
    @Transactional
    public void closeRestaurante(Long id){
        Restaurante restaurante = buscaOuFalhar(id);
        restaurante.close();
    }

}
