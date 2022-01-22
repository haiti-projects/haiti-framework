package dev.struchkov.haiti.context.repository.simple;

import dev.struchkov.haiti.context.domain.BasicEntity;

import java.util.Collection;
import java.util.List;

public interface MultipleOperation<T extends BasicEntity<K>, K> {

    List<T> saveAll(Collection<T> entities);

    void deleteAllById(Collection<K> primaryKeys);

    List<T> findAllById(Collection<K> ids);

}
