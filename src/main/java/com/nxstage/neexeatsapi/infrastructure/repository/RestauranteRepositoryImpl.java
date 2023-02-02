package com.nxstage.neexeatsapi.infrastructure.repository;

import com.nxstage.neexeatsapi.domain.model.Restaurante;
import com.nxstage.neexeatsapi.domain.repository.RestauranteRepository;
import com.nxstage.neexeatsapi.domain.repository.RestauranteRepositoryQueries;
import com.nxstage.neexeatsapi.infrastructure.repository.spec.RestauranteSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {
    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> find(String nome, BigDecimal tInicial, BigDecimal tFinal){
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteria.from(Restaurante.class); //from Restarante

        var predicates = new ArrayList<Predicate>();

        if(StringUtils.hasText(nome)){
            predicates.add(builder.like(root.get("nome"),"%"+nome+"%"));
        }
        if(tInicial!=null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"),tInicial));
        }
        if(tFinal!=null){
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"),tFinal));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurante> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<Restaurante> findSemTaxa(String nome) {
        return restauranteRepository.findAll(RestauranteSpecs.semTaxa()
                .and(RestauranteSpecs.comNomeSemalhante(nome)));
    }
}
