package dev.struchkov.godfather.context.service;

import lombok.NonNull;
import dev.struchkov.godfather.context.domain.content.Message;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс взаимодйствия с наследниками текстовых сообщений пользователей.
 *
 * @author upagge [08/07/2019]
 */
public interface MessageService<T extends Message> {

    void add(@NonNull T event);

    /**
     * Получить список сообщений за заданный временной интервал
     *
     * @param dateFrom Начало интервала
     * @param dateTo   Конец интервала
     * @return Список сообщений
     */
    List<T> getByAddDateTime(@NonNull LocalDateTime dateFrom, @NonNull LocalDateTime dateTo);

    /**
     * Получить список ПОСЛЕДНИХ сообщений для каждого пользователя за заданных временной интервал
     *
     * @param dateFrom Начало интервала
     * @param dateTo   Конец интервала
     * @return Список сообщений
     */
    List<T> getLastEventByCreateDateTime(@NonNull LocalDateTime dateFrom, @NonNull LocalDateTime dateTo);

    List<T> getLastEventByAddDateTime(@NonNull LocalDateTime dateFrom, @NonNull LocalDateTime dateTo);

    /**
     * Возвращает новые сообщения от последнего запроса.
     */
    List<T> getNewMessage();

    void deleteAllByAddDateBetween(@NonNull LocalDateTime dateFrom, @NonNull LocalDateTime dateTo);

    void deleteAllByAddDateBefore(@NonNull LocalDateTime date);

    void deleteAllByAddDateAfter(@NonNull LocalDateTime date);

}
