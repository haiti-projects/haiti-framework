package dev.struchkov.haiti.context.service.simple;

import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;

import java.util.Optional;

public interface FilterService<Entity, Filter> {

    /**
     * Получить все элементы по заданному фильтру.
     *
     * @param filter     Фильтр
     * @param pagination Пагинация
     */
    Sheet<Entity> getAll(Filter filter, Pagination pagination);

    /**
     * Возвращает первый найденный объект по фильтру.
     *
     * @param filter Объект фильтра
     */
    Optional<Entity> getFirst(Filter filter);

    /**
     * Проверка на наличие хотя бы одной сущности, которая удовлетворяет определенному набору параметров.
     *
     * @param filter Набор параметров
     */
    boolean exists(Filter filter);

    /**
     * Получить количество сущностей, которые удовлетворяют определенному набору параметров.
     *
     * @param filter Набор параметров
     */
    long count(Filter filter);

}
