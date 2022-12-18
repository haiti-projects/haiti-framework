package dev.struchkov.godfather.quarkus.context.service;

import dev.struchkov.godfather.main.domain.BoxAnswer;
import dev.struchkov.godfather.main.domain.SendType;
import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

/**
 * Интерфейс для отправки ответов пользователю.
 *
 * @author upagge [08/07/2019]
 */
public interface Sending {

    /**
     * Отправляет сообщение пользователю
     *
     * @param boxAnswer Объект с данными, которые необходимо отправить
     */
    Uni<Void> send(@NotNull BoxAnswer boxAnswer);

    void addPreSendProcess(@NotNull PreSendProcessing processing);

    /**
     * Возвращает тип объекта отправляющего ответ пользователя. В зависимости от типа ответ будет отправлен с помощью
     * разных методов.
     */
    SendType getType();

}