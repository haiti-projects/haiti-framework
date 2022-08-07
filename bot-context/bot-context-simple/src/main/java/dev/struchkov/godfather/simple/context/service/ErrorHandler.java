package dev.struchkov.godfather.simple.context.service;

import dev.struchkov.godfather.main.domain.content.Message;

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
    void handle(Message message, Exception e);

}
