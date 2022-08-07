package dev.struchkov.godfather.quarkus.data.preser;

import io.smallrye.mutiny.Uni;

import java.util.Map;

/**
 * Интерфейс для сохранения и взаимодейтсвия с ответами прав пользователя.
 *
 * @author upagge [11/07/2019]
 */
public interface Preservable<S> {

    /**
     * Сохранение данных для пользователя
     *
     * @param personId Идентификатор пользователя
     * @param save     Объект данных
     */
    Uni<Void> save(Long personId, String key, S save);

    Uni<S> getByKey(Long personId, String key);

    Uni<Map<String, S>> getAllSaveElement(Long personId);

}
