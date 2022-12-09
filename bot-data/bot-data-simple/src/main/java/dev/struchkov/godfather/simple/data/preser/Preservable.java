package dev.struchkov.godfather.simple.data.preser;

import java.util.Map;
import java.util.Optional;

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
    void save(String personId, String key, S save);

    Optional<S> getByKey(String personId, String key);

    Map<String, S> getAllSaveElement(String personId);

}
