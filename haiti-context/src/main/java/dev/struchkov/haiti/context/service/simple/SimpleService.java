package dev.struchkov.haiti.context.service.simple;

import lombok.NonNull;

import java.util.Optional;

/**
 * Базовый контракт для сервисов.
 *
 * @param <T> Сущность сервиса.
 */
public interface SimpleService<T, K> {

    T create(@NonNull T entity);

    T update(@NonNull T entity);

    Optional<T> getById(@NonNull K primaryKey);

    T getByIdOrThrow(@NonNull K primaryKey);

    boolean existsById(@NonNull K primaryKey);

    void deleteById(@NonNull K primaryKey);

}
