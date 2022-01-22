package dev.struchkov.haiti.core.service;

import dev.struchkov.haiti.context.domain.BasicEntity;
import dev.struchkov.haiti.context.domain.ExistsContainer;
import dev.struchkov.haiti.context.exception.NotFoundException;
import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.context.repository.SimpleManagerRepository;
import dev.struchkov.haiti.context.service.SimpleManagerService;
import dev.struchkov.haiti.core.util.ServiceOperation;
import dev.struchkov.haiti.utils.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractSimpleManagerService<Entity extends BasicEntity<Key>, Key> implements SimpleManagerService<Entity, Key> {

    protected final SimpleManagerRepository<Entity, Key> repository;

    protected AbstractSimpleManagerService(SimpleManagerRepository<Entity, Key> repository) {
        this.repository = repository;
    }

    @Override
    public void deleteAllById(Collection<Key> ids) {
        Assert.isNotNull(ids);
        ServiceOperation.deleteAllById(repository, ids);
    }

    @Override
    public Sheet<Entity> getAll(Pagination pagination) {
        Assert.isNotNull(pagination);
        return ServiceOperation.getAll(repository, pagination);
    }

    @Override
    public Optional<Entity> getById(Key id) {
        Assert.isNotNull(id);
        return ServiceOperation.getById(repository, id);
    }

    @Override
    public Entity getByIdOrThrow(Key id) {
        Assert.isNotNull(id);
        return getById(id).orElseThrow(NotFoundException.supplier("Объект не найден. Идентификатор: ", id));
    }

    @Override
    public boolean existsById(Key id) {
        Assert.isNotNull(id);
        return ServiceOperation.existsById(repository, id);
    }

    @Override
    public void deleteById(Key id) {
        Assert.isNotNull(id);
        ServiceOperation.deleteById(repository, id);
    }

    @Override
    public List<Entity> createAll(Collection<Entity> entities) {
        Assert.isNotNull(entities);
        return entities.stream().map(this::create).collect(Collectors.toList());
    }

    @Override
    public List<Entity> updateAll(Collection<Entity> entities) {
        Assert.isNotNull(entities);
        return entities.stream().map(this::update).collect(Collectors.toList());
    }

    @Override
    public ExistsContainer<Entity, Key> existsById(Collection<Key> ids) {
        Assert.isNotNull(ids);
        return ServiceOperation.existsContainerById(repository, ids);
    }

}
