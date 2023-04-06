package com.nxstage.neexeatsapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nxstage.neexeatsapi.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Kitchen {
    @Id
    @NotNull(groups = Groups.CozinhaId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "kitchen")
    private List<Restaurante> restaurantes = new ArrayList<>();

}
