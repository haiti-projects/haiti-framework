package org.sadtech.haiti.database.repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.sadtech.haiti.context.repository.simple.CrudOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractCrudOperation<T, K> implements CrudOperation<T, K> {

    private final JpaRepository<T, K> jpaRepository;

    @Override
    public T save(@NonNull T entity) {
        return OperationJpa.save(jpaRepository, entity);
    }

    @Override
    public Optional<T> findById(@NonNull K primaryKey) {
        return OperationJpa.findById(jpaRepository, primaryKey);
    }

    @Override
    public boolean existsById(@NonNull K primaryKey) {
        return OperationJpa.existsById(jpaRepository, primaryKey);
    }

    @Override
    public void deleteById(@NonNull K primaryKey) {
        OperationJpa.deleteById(jpaRepository, primaryKey);
    }

}
