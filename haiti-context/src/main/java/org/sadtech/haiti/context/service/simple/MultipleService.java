package org.sadtech.haiti.context.service.simple;

import lombok.NonNull;

import java.util.Collection;
import java.util.List;

public interface MultipleService<T, K> {

    List<T> createAll(@NonNull Collection<T> entities);

    List<T> updateAll(@NonNull Collection<T> entities);

    void deleteAllById(@NonNull Collection<K> ids);

}
