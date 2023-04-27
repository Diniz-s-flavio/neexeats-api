package com.nxstage.neexeatsapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nxstage.neexeatsapi.core.validation.Groups;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class Kitchen {
    @Id
    //@NotNull(groups = Groups.CozinhaId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @NonNull
    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "kitchen")
    private List<Restaurante> restaurantes = new ArrayList<>();

}
