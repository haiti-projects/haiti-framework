package org.sadtech.haiti.core.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.sadtech.haiti.context.page.Pagination;
import org.sadtech.haiti.context.page.Sheet;
import org.sadtech.haiti.context.repository.SimpleManagerRepository;
import org.sadtech.haiti.context.service.SimpleManagerService;
import org.sadtech.haiti.core.util.ServiceOperation;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractSimpleManagerService<T, K> implements SimpleManagerService<T, K> {

    private final SimpleManagerRepository<T, K> repository;

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

}
