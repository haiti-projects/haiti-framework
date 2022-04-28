package dev.struchkov.godfather.context.service.sender;

import dev.struchkov.godfather.context.domain.BoxAnswer;
import org.jetbrains.annotations.NotNull;

/**
 * Интерфейс для отправки ответов пользователю.
 *
 * @author upagge [08/07/2019]
 */
public interface Sending {

    /**
     * Отрпавляет ответ пользователю
     *
     * @param personId  Идентификатор пользователя
     * @param boxAnswer Объект с данными, которые необходимо отправить
     */
    void send(@NotNull Long personId, @NotNull BoxAnswer boxAnswer);

    void send(@NotNull Long contentId, @NotNull Long personId, @NotNull BoxAnswer boxAnswer);

    /**
     * Возвращает тип объекта отправляющего ответ пользователя. В зависимости от типа ответ будет отправлен с помощью
     * разных методов.
     */
    SendType getType();

}
