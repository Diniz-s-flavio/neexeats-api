package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.exception.NegocioException;
import com.nxstage.neexeatsapi.domain.exception.PedidoNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.model.*;
import com.nxstage.neexeatsapi.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmissaoPerdidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private CadastroRestauranteService cadastroRestaurante;
    @Autowired
    private CadastroCidadeService cadastroCidade;
    @Autowired
    private CadastroUsuarioService cadastroUsuario;
    @Autowired
    private CadastroProdutoService cadastroProduto;
    @Autowired
    private CadastroFormaPagService cadastroFormaPagService;

    @Transactional
    public Pedido emitir(Pedido pedido){
        validarPedido(pedido);
        validarItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcTotalValue();

        return pedidoRepository.save(pedido);
    }

    private void validarPedido(Pedido pedido) {
        Cidade cidade = cadastroCidade.buscarOuFalhar(
                pedido.getEnderecoEntrega().getCidade().getId());
        Usuario cliente = cadastroUsuario.buscarOuFalhar(
                pedido.getCliente().getId());
        Restaurante restaurante = cadastroRestaurante.buscaOuFalhar(
                pedido.getRestaurante().getId());
        FormaPag formaPag = cadastroFormaPagService.buscarOuFalhar(
                pedido.getFormaPag().getId());

        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setFormaPag(formaPag);

        if (restaurante.naoAceitaFormaPag(formaPag)){
            throw new NegocioException(
                    String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                            formaPag.getDescricao()));
        }
    }
    private void validarItens(Pedido pedido) {
        pedido.getItens().forEach(item->{
            Produto produto = cadastroProduto.buscarOuFalhar(
                    pedido.getRestaurante().getId(),item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }

    public Pedido buscarOuFalhar(Long pedidoId){
        return  pedidoRepository.findById(pedidoId)
                .orElseThrow(()-> new PedidoNaoEncontradaException(pedidoId));
    }
}
