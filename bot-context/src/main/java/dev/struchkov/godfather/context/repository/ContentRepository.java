package dev.struchkov.godfather.context.repository;

import dev.struchkov.godfather.context.domain.content.Message;
import org.jetbrains.annotations.NotNull;

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
    T add(@NotNull T content);

    /**
     * Получить все сообщения за определенный временной диапазон
     *
     * @param dateFrom Начало временного диапазона
     * @param dateTo   Конец диапазона
     * @return Список сообщений
     */
    List<T> betweenByCreateDateTime(@NotNull LocalDateTime dateFrom, @NotNull LocalDateTime dateTo);

    List<T> betweenByAddDateTime(@NotNull LocalDateTime dateFrom, @NotNull LocalDateTime dateTo);

    /**
     * Удаляет данные за указанный период
     *
     * @param dateFrom Дата начала
     * @param dateTo   Дата окончания
     */
    void deleteAllByAddDateBetween(@NotNull LocalDateTime dateFrom, @NotNull LocalDateTime dateTo);

    void deleteAllByAddDateBefore(@NotNull LocalDateTime date);

    void deleteAllByAddDateAfter(@NotNull LocalDateTime date);

}
