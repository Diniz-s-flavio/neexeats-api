package com.nxstage.neexeatsapi.infrastructure.repository.spec;

import com.nxstage.neexeatsapi.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteSpecs {
    public static Specification<Restaurante> semTaxa(){
        return (root, query, builder)-> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }
    public static  Specification<Restaurante> comNomeSemalhante(String nome){
        return (root, query, builder) -> builder.like(root.get("nome"),"%"+ nome + "%");
    }
}
