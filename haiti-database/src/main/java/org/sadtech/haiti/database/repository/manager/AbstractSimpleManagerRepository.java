package org.sadtech.haiti.database.repository.manager;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.sadtech.haiti.database.repository.OperationJpa;
import org.sadtech.haiti.context.page.Pagination;
import org.sadtech.haiti.context.page.Sheet;
import org.sadtech.haiti.context.repository.SimpleManagerRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractSimpleManagerRepository<T, K> implements SimpleManagerRepository<T, K> {

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

    @Override
    public List<T> saveAll(@NonNull Collection<T> entities) {
        return OperationJpa.saveAll(jpaRepository, entities);
    }

    @Override
    public void deleteAllById(@NonNull Collection<K> primaryKeys) {
        OperationJpa.deleteAllById(jpaRepository, primaryKeys);
    }

    @Override
    public Sheet<T> findAll(@NonNull Pagination pagination) {
        return OperationJpa.findAll(jpaRepository, pagination);
    }

}
