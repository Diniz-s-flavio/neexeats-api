package com.nxstage.neexeatsapi.infrastructure.repository;

import com.nxstage.neexeatsapi.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID>  extends SimpleJpaRepository<T, ID>
    implements CustomJpaRepository<T,ID> {

    private EntityManager manager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.manager =entityManager;
    }

    @Override
    public void detach(T entity) {
            manager.detach(entity);
    }
}
