package com.nxstage.neexeatsapi.infrastructure.repository.spec;

import com.nxstage.neexeatsapi.domain.model.Pedido;
import com.nxstage.neexeatsapi.domain.repository.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class PedidoSpecs {
    public static Specification<Pedido> useFilter(PedidoFilter filter){
        return (root, query,builder)-> {
            if(Pedido.class.equals(query.getResultType())){
                root.fetch("restaurante").fetch("kitchen");
                root.fetch("cliente");
            }

            var predicates = new ArrayList<Predicate>();
            if (filter.getClienteId() != null){
                predicates.add(builder.equal(root.get("cliente"),
                        filter.getClienteId()));
            }
            if (filter.getRestauranteId() != null){
                predicates.add(builder.equal(root.get("restaurante"),
                        filter.getRestauranteId()));
            }
            if (filter.getDataCriacaoInicio() != null){
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
                        filter.getDataCriacaoInicio()));
            }if (filter.getDataCriacaoFim() != null){
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
                        filter.getDataCriacaoFim()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
