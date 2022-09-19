package dev.struchkov.godfather.quarkus.context.service;

import dev.struchkov.godfather.main.domain.content.Message;
import io.smallrye.mutiny.Uni;

/**
 * Используется для перехвата исключений, которые возникают при обработке юнитов.
 */
public interface ErrorHandler {

    /**
     * Метод, который должен как-то обработать исключение.
     *
     * @param message Сообщение, после которого возникло исключение.
     * @param e       Объект исключения.
     */
    Uni<Void> handle(Message message, Throwable e);

}
