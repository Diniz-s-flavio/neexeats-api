package com.nxstage.neexeatsapi.domain.model;

import com.nxstage.neexeatsapi.domain.exception.NegocioException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    @CreationTimestamp
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataEntrega;
    private OffsetDateTime dataCancelamento;

    @Embedded
    private Endereco enderecoEntrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;

    @OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL)
    private List<ItemPedido> itens= new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurante restaurante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private FormaPag formaPag;

    @ManyToOne
     @JoinColumn(name = "usuario_cliente_id",nullable = false)
    private Usuario cliente;

    public void calcTotalValue(){
        getItens().forEach(ItemPedido::calcTotalCost);

        this.subtotal =getItens().stream()
                .map(iten-> iten.getPrecoTotal())
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        this.valorTotal =this.subtotal.add(this.taxaFrete);
    }

    public void confirmar(){
        setStatus(StatusPedido.CONFIRMADO);
        setDataConfirmacao(OffsetDateTime.now());
    }
    public void cancelar(){
        setStatus(StatusPedido.CANCELADO);
        setDataConfirmacao(OffsetDateTime.now());
    }
    public void enviar(){
        setStatus(StatusPedido.ENTREGUE);
        setDataConfirmacao(OffsetDateTime.now());
    }

    private void setStatus(StatusPedido newStatus){
        if (getStatus().cantChangeTo(newStatus)){
            throw new NegocioException(
                    String.format("Status do Pedido %d não pode ser alterado de %s para %s",
                            getId(),
                            getStatus().getDescricao(),
                            newStatus.getDescricao()));
        }
        this.status = newStatus;
    }
}
