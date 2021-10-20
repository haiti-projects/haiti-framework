package dev.struchkov.haiti.context.repository.simple;

import lombok.NonNull;
import dev.struchkov.haiti.context.domain.BasicEntity;

import java.util.Collection;
import java.util.List;

public interface MultipleOperation<T extends BasicEntity<K>, K> {

    List<T> saveAll(@NonNull Collection<T> entities);

    void deleteAllById(@NonNull Collection<K> primaryKeys);

    List<T> findAllById(@NonNull Collection<K> ids);

}
