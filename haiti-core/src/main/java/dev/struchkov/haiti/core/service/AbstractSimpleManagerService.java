package dev.struchkov.haiti.core.service;

import dev.struchkov.haiti.context.domain.BasicEntity;
import dev.struchkov.haiti.context.domain.ExistsContainer;
import dev.struchkov.haiti.context.exception.NotFoundException;
import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.context.repository.SimpleManagerRepository;
import dev.struchkov.haiti.context.service.SimpleManagerService;
import dev.struchkov.haiti.core.util.ServiceOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractSimpleManagerService<T extends BasicEntity<K>, K> implements SimpleManagerService<T, K> {

    protected final SimpleManagerRepository<T, K> repository;

    @Override
    public void deleteAllById(@NonNull Collection<K> primaryKeys) {
        ServiceOperation.deleteAllById(repository, primaryKeys);
    }

    @Override
    public Sheet<T> getAll(@NonNull Pagination pagination) {
        return ServiceOperation.getAll(repository, pagination);
    }

    @Override
    public Optional<T> getById(@NonNull K primaryKey) {
        return ServiceOperation.getById(repository, primaryKey);
    }

    @Override
    public T getByIdOrThrow(@NonNull K primaryKey) {
        return getById(primaryKey).orElseThrow(NotFoundException.supplier("Объект не найден. Идентификатор: ", primaryKey));
    }

    @Override
    public boolean existsById(@NonNull K primaryKey) {
        return ServiceOperation.existsById(repository, primaryKey);
    }

    @Override
    public void deleteById(@NonNull K primaryKey) {
        ServiceOperation.deleteById(repository, primaryKey);
    }

    @Override
    public List<T> createAll(@NonNull Collection<T> entities) {
        return entities.stream().map(this::create).collect(Collectors.toList());
    }

    @Override
    public List<T> updateAll(@NonNull Collection<T> entities) {
        return entities.stream().map(this::update).collect(Collectors.toList());
    }

    @Override
    public ExistsContainer<T, K> existsById(@NonNull Collection<K> ids) {
        return ServiceOperation.existsContainerById(repository, ids);
    }

}
