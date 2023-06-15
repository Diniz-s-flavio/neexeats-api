package com.nxstage.neexeatsapi.infrastructure.service;

import com.nxstage.neexeatsapi.domain.filter.VendaDiariaFilter;
import com.nxstage.neexeatsapi.domain.model.Pedido;
import com.nxstage.neexeatsapi.domain.model.StatusPedido;
import com.nxstage.neexeatsapi.domain.model.dto.VendaDiaria;
import com.nxstage.neexeatsapi.domain.repository.VendaQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<VendaDiaria> getDailySales(VendaDiariaFilter filter, String timeOffset) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);
        var predicates = new ArrayList<Predicate>();

        var  functionConvertTzDateDataCriacao = builder.function("convert_tz",
                Date.class, root.get("DataCriacao"),
                builder.literal("+00:00"), builder.literal(timeOffset));

        var  functionDateDataCriacao = builder.function("date",
                Date.class, functionConvertTzDateDataCriacao);

        var selection = builder.construct(VendaDiaria.class,
                functionDateDataCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));

        if (filter.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante"),
                    filter.getRestauranteId()));
        }
        if (filter.getDataCriacaoInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
                    filter.getDataCriacaoInicio()));
        }
        if (filter.getDataCriacaoFim() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
                    filter.getDataCriacaoFim()));
        }

        predicates.add(root.get("status").in(
                StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionDateDataCriacao);

        return manager.createQuery(query).getResultList();
    }
}
