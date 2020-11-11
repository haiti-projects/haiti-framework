package org.sadtech.haiti.database.repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.sadtech.haiti.context.repository.simple.MultipleOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractMultipleOperation<T, K> implements MultipleOperation<T, K> {

    private final JpaRepository<T, K> jpaRepository;

    @Override
    public List<T> saveAll(@NonNull Collection<T> entities) {
        return OperationJpa.saveAll(jpaRepository, entities);
    }

    @Override
    public void deleteAllById(@NonNull Collection<K> primaryKeys) {
        OperationJpa.deleteAllById(jpaRepository, primaryKeys);
    }

}
