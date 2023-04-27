package com.nxstage.neexeatsapi.domain.model;

import com.nxstage.neexeatsapi.core.validation.Groups;
import com.nxstage.neexeatsapi.core.validation.ValorZeroInclueDescricao;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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

    //@NotBlank
    @NonNull
    @Column(nullable = false)
    private String nome;

    //@NotNull
    @NonNull
    //@PositiveOrZero
    @Column(name = "taxa_frete",nullable = false)
    private BigDecimal taxaFrete;

    //@JsonIgnore
    //@Valid
    //@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    //@NotNull
    @NonNull
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchen_id",nullable = false)//define o nome na tabela restaurante da foreing Key
    private Kitchen kitchen;

    @Embedded
    private Endereco endereco;

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
    private List<FormaPag> formasPag = new ArrayList<>();

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

}
