package dev.struchkov.godfather.simple.context.service;

import dev.struchkov.godfather.main.domain.BoxAnswer;
import dev.struchkov.godfather.main.domain.SendType;
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
    void send(@NotNull String personId, @NotNull BoxAnswer boxAnswer);

    void addPreSendProcess(@NotNull PreSendProcessing processing);

    /**
     * Возвращает тип объекта отправляющего ответ пользователя. В зависимости от типа ответ будет отправлен с помощью
     * разных методов.
     */
    SendType getType();

}
