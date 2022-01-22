package dev.struchkov.haiti.core.service;

import dev.struchkov.haiti.context.repository.simple.CrudOperation;
import dev.struchkov.haiti.context.service.simple.SimpleService;
import dev.struchkov.haiti.core.util.ServiceOperation;
import dev.struchkov.haiti.utils.Assert;

import java.util.Optional;

public abstract class AbstractSimpleService<Entity, Key> implements SimpleService<Entity, Key> {

    private final CrudOperation<Entity, Key> crudOperation;

    protected AbstractSimpleService(CrudOperation<Entity, Key> crudOperation) {
        this.crudOperation = crudOperation;
    }

    @Override
    public Optional<Entity> getById(Key id) {
        Assert.isNotNull(id);
        return ServiceOperation.getById(crudOperation, id);
    }

    @Override
    public boolean existsById(Key id) {
        Assert.isNotNull(id);
        return ServiceOperation.existsById(crudOperation, id);
    }

    @Override
    public void deleteById(Key id) {
        Assert.isNotNull(id);
        ServiceOperation.deleteById(crudOperation, id);
    }

}
