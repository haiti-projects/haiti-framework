package dev.struchkov.haiti.core.service;

import dev.struchkov.haiti.context.domain.BasicEntity;
import dev.struchkov.haiti.context.domain.ExistsContainer;
import dev.struchkov.haiti.context.repository.simple.MultipleOperation;
import dev.struchkov.haiti.context.service.simple.MultipleService;
import dev.struchkov.haiti.context.service.simple.SimpleService;
import dev.struchkov.haiti.core.util.ServiceOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractMultipleService<T extends BasicEntity<K>, K> implements MultipleService<T, K> {

    private final SimpleService<T, K> simpleService;
    private final MultipleOperation<T, K> multipleOperation;

    @Override
    public void deleteAllById(@NonNull Collection<K> ids) {
        multipleOperation.deleteAllById(ids);
    }

    @Override
    public List<T> createAll(@NonNull Collection<T> entities) {
        return entities.stream().map(simpleService::create).collect(Collectors.toList());
    }

    @Override
    public List<T> updateAll(@NonNull Collection<T> entities) {
        return entities.stream().map(simpleService::update).collect(Collectors.toList());
    }

    @Override
    public ExistsContainer<T, K> existsById(@NonNull Collection<K> ids) {
        return ServiceOperation.existsContainerById(multipleOperation, ids);
    }

}
