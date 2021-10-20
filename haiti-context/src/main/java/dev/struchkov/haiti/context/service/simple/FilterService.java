package dev.struchkov.haiti.context.service.simple;

import lombok.NonNull;
import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;

import java.util.Optional;

public interface FilterService<T, F> {

    /**
     * Получить все элементы по заданному фильтру.
     *
     * @param filter     Фильтр
     * @param pagination Пагинация
     */
    Sheet<T> getAll(@NonNull F filter, @NonNull Pagination pagination);

    /**
     * Возвращает первый найденный объект по фильтру.
     *
     * @param filter Объект фильтра
     */
    Optional<T> getFirst(@NonNull F filter);

    /**
     * Проверка на наличие хотя бы одной сущности, которая удовлетворяет определенному набору параметров.
     *
     * @param filter Набор параметров
     */
    boolean exists(@NonNull F filter);

    /**
     * Получить количество сущностей, которые удовлетворяют определенному набору параметров.
     *
     * @param filter Набор параметров
     */
    long count(@NonNull F filter);

}
