package dev.struchkov.haiti.core.service;

import dev.struchkov.haiti.context.repository.simple.CrudOperation;
import dev.struchkov.haiti.context.service.simple.SimpleService;
import dev.struchkov.haiti.core.util.ServiceOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractSimpleService<T, K> implements SimpleService<T, K> {

    private final CrudOperation<T, K> crudOperation;

    @Override
    public Optional<T> getById(@NonNull K primaryKey) {
        return ServiceOperation.getById(crudOperation, primaryKey);
    }

    @Override
    public boolean existsById(@NonNull K primaryKey) {
        return ServiceOperation.existsById(crudOperation, primaryKey);
    }

    @Override
    public void deleteById(@NonNull K primaryKey) {
        ServiceOperation.deleteById(crudOperation, primaryKey);
    }

}
