package org.sadtech.haiti.core.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.sadtech.haiti.context.repository.simple.MultipleOperation;
import org.sadtech.haiti.context.service.simple.MultipleService;
import org.sadtech.haiti.context.service.simple.SimpleService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractMultipleService<T, K> implements MultipleService<T, K> {

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

}
