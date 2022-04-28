package dev.struchkov.godfather.context.service;

import dev.struchkov.godfather.context.domain.content.Message;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс взаимодйствия с наследниками текстовых сообщений пользователей.
 *
 * @author upagge [08/07/2019]
 */
public interface MessageService<T extends Message> {

    void add(@NotNull T event);

    /**
     * Получить список сообщений за заданный временной интервал
     *
     * @param dateFrom Начало интервала
     * @param dateTo   Конец интервала
     * @return Список сообщений
     */
    List<T> getByAddDateTime(@NotNull LocalDateTime dateFrom, @NotNull LocalDateTime dateTo);

    /**
     * Получить список ПОСЛЕДНИХ сообщений для каждого пользователя за заданных временной интервал
     *
     * @param dateFrom Начало интервала
     * @param dateTo   Конец интервала
     * @return Список сообщений
     */
    List<T> getLastEventByCreateDateTime(@NotNull LocalDateTime dateFrom, @NotNull LocalDateTime dateTo);

    List<T> getLastEventByAddDateTime(@NotNull LocalDateTime dateFrom, @NotNull LocalDateTime dateTo);

    /**
     * Возвращает новые сообщения от последнего запроса.
     */
    List<T> getNewMessage();

    void deleteAllByAddDateBetween(@NotNull LocalDateTime dateFrom, @NotNull LocalDateTime dateTo);

    void deleteAllByAddDateBefore(@NotNull LocalDateTime date);

    void deleteAllByAddDateAfter(@NotNull LocalDateTime date);

}
