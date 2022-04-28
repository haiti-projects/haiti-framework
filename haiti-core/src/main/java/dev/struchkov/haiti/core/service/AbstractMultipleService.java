package dev.struchkov.haiti.core.service;

import dev.struchkov.haiti.context.domain.BasicEntity;
import dev.struchkov.haiti.context.domain.ExistsContainer;
import dev.struchkov.haiti.context.repository.simple.MultipleOperation;
import dev.struchkov.haiti.context.service.simple.MultipleService;
import dev.struchkov.haiti.context.service.simple.SimpleService;
import dev.struchkov.haiti.core.util.ServiceOperation;
import dev.struchkov.haiti.utils.Inspector;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMultipleService<Entity extends BasicEntity<Key>, Key> implements MultipleService<Entity, Key> {

    private final SimpleService<Entity, Key> simpleService;
    private final MultipleOperation<Entity, Key> multipleOperation;

    protected AbstractMultipleService(SimpleService<Entity, Key> simpleService, MultipleOperation<Entity, Key> multipleOperation) {
        this.simpleService = simpleService;
        this.multipleOperation = multipleOperation;
    }

    @Override
    public void deleteAllById(Collection<Key> ids) {
        Inspector.isNotNull(ids);
        multipleOperation.deleteAllById(ids);
    }

    @Override
    public List<Entity> createAll(Collection<Entity> entities) {
        Inspector.isNotNull(entities);
        return entities.stream().map(simpleService::create).collect(Collectors.toList());
    }

    @Override
    public List<Entity> updateAll(Collection<Entity> entities) {
        Inspector.isNotNull(entities);
        return entities.stream().map(simpleService::update).collect(Collectors.toList());
    }

    @Override
    public ExistsContainer<Entity, Key> existsById(Collection<Key> ids) {
        Inspector.isNotNull(ids);
        return ServiceOperation.existsContainerById(multipleOperation, ids);
    }

}
