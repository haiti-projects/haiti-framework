package dev.struchkov.haiti.context.service.simple;

import java.util.Optional;

/**
 * Базовый контракт для сервисов.
 *
 * @param <Entity> Сущность сервиса.
 */
public interface SimpleService<Entity, Key> {

    Entity create(Entity entity);

    Entity update(Entity entity);

    Optional<Entity> getById(Key id);

    Entity getByIdOrThrow(Key id);

    boolean existsById(Key id);

    void deleteById(Key id);

}
