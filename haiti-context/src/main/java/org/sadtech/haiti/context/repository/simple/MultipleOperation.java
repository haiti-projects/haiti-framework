package org.sadtech.haiti.context.repository.simple;

import lombok.NonNull;

import java.util.Collection;
import java.util.List;

public interface MultipleOperation<T, K> {

    List<T> saveAll(@NonNull Collection<T> entities);

    void deleteAllById(@NonNull Collection<K> primaryKeys);

}
