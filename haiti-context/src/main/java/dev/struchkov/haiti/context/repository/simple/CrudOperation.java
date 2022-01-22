package dev.struchkov.haiti.context.repository.simple;

import java.util.Optional;

/**
 * Контракт для реализации объекта, который будет взаимодействовать с хранилищем данных.
 *
 * @param <Entity> Класс сущности
 * @param <Key> Класс идентификатора
 * @author upagge
 */
public interface CrudOperation<Entity, Key> {

    /**
     * Сохраняет сущность в базу данных. Не проверяет на наличие в хранилище.
     * Если сущность с подобным идентификатором уже есть, то необходимо ее перезаписать.
     *
     * @param entity Сущность для сохранения
     * @return Сохраненная сущность
     */
    Entity save(Entity entity);

    Optional<Entity> findById(Key id);

    boolean existsById(Key id);

    void deleteById(Key id);

}
