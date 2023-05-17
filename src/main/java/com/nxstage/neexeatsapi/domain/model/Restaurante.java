package com.nxstage.neexeatsapi.domain.model;

import com.nxstage.neexeatsapi.core.validation.ValorZeroInclueDescricao;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ValorZeroInclueDescricao(valorField =  "taxaFrete"
        , descricaoField = "nome", descricaoObrigatoria =  "Frete Gratis")
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String nome;

    @NonNull
    @Column(name = "taxa_frete",nullable = false)
    private BigDecimal taxaFrete;


    @NonNull
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchen_id",nullable = false)//define o nome na tabela restaurante da foreing Key
    private Kitchen kitchen;

    @Embedded
    private Endereco endereco;

    private Boolean ativo = Boolean.TRUE;
    private Boolean aberto = Boolean.TRUE;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataAtualizacao;

    @ManyToMany
    @JoinTable(name = "restaurante_forma_pag",
                joinColumns = @JoinColumn(name = "restaurante_id"),
                inverseJoinColumns = @JoinColumn(name = "forma_pag_id"))
    private Set<FormaPag> formaPag = new HashSet<>();

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    public void  ativar(){
        setAtivo(true);
    }
    public void  inativar(){
        setAtivo(false);
    }
    public void open(){
        setAberto(true);
    }
    public void close(){
        setAberto(false);
    }

    public boolean adicionarFormaPag(FormaPag formaPag){
        return getFormaPag().add(formaPag);
    }

    public boolean removerFormaPag(FormaPag formaPag){
        return  getFormaPag().remove(formaPag);
    }

}
