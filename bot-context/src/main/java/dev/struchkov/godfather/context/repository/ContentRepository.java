package dev.struchkov.godfather.context.repository;

import lombok.NonNull;
import dev.struchkov.godfather.context.domain.content.Message;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс взаимодействия со всеми наследниками текстовых запросов пользователей.
 *
 * @author upagge [08/07/2019]
 */
public interface ContentRepository<T extends Message> {

    /**
     * Добавить сообщение в хранилище
     *
     * @param content Объект сообщени
     * @return Идентификатор сообщения в хранилище
     */
    T add(@NonNull T content);

    /**
     * Получить все сообщения за определенный временной диапазон
     *
     * @param dateFrom Начало временного диапазона
     * @param dateTo   Конец диапазона
     * @return Список сообщений
     */
    List<T> betweenByCreateDateTime(@NonNull LocalDateTime dateFrom, @NonNull LocalDateTime dateTo);

    List<T> betweenByAddDateTime(@NonNull LocalDateTime dateFrom, @NonNull LocalDateTime dateTo);

    /**
     * Удаляет данные за указанный период
     *
     * @param dateFrom Дата начала
     * @param dateTo   Дата окончания
     */
    void deleteAllByAddDateBetween(@NonNull LocalDateTime dateFrom, @NonNull LocalDateTime dateTo);

    void deleteAllByAddDateBefore(@NonNull LocalDateTime date);

    void deleteAllByAddDateAfter(@NonNull LocalDateTime date);

}
