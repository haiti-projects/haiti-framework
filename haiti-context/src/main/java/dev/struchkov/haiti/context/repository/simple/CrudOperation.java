package dev.struchkov.haiti.context.repository.simple;

import lombok.NonNull;

import java.util.Optional;

/**
 * Контракт для реализации объекта, который будет взаимодействовать с хранилищем данных.
 *
 * @param <T> Класс сущности
 * @param <K> Класс идентификатора
 * @author upagge
 */
public interface CrudOperation<T, K> {

    /**
     * Сохраняет сущность в базу данных. Не проверяет на наличие в хранилище.
     * Если сущность с подобным идентификатором уже есть, то необходимо ее перезаписать.
     *
     * @param entity Сущность для сохранения
     * @return Сохраненная сущность
     */
    T save(@NonNull T entity);

    Optional<T> findById(@NonNull K primaryKey);

    boolean existsById(@NonNull K primaryKey);

    void deleteById(@NonNull K primaryKey);

}
