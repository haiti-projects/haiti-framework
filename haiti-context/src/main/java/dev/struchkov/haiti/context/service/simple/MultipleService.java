package dev.struchkov.haiti.context.service.simple;

import dev.struchkov.haiti.context.domain.BasicEntity;
import dev.struchkov.haiti.context.domain.ExistsContainer;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;

public interface MultipleService<T extends BasicEntity<K>, K> {

    List<T> createAll(@NonNull Collection<T> entities);

    List<T> updateAll(@NonNull Collection<T> entities);

    void deleteAllById(@NonNull Collection<K> ids);

    ExistsContainer<T, K> existsById(@NonNull Collection<K> ids);

}
