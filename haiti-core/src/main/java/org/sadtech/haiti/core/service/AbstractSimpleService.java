package org.sadtech.haiti.core.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.sadtech.haiti.context.repository.simple.CrudOperation;
import org.sadtech.haiti.context.service.simple.SimpleService;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractSimpleService<T, K> implements SimpleService<T, K> {

    private final CrudOperation<T, K> crudOperation;

    @Override
    public Optional<T> getById(@NonNull K primaryKey) {
        return crudOperation.findById(primaryKey);
    }

    @Override
    public boolean existsById(@NonNull K primaryKey) {
        return crudOperation.existsById(primaryKey);
    }

    @Override
    public void deleteById(@NonNull K primaryKey) {
        crudOperation.deleteById(primaryKey);
    }

}
