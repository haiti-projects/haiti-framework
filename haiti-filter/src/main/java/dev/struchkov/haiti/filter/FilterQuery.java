package dev.struchkov.haiti.filter;

import java.util.Set;

public interface FilterQuery {

    /**
     * Добавляет в запрос поиск по диапазону.
     *
     * @param field Название поля в elasticsearch
     * @param from  Начало интервала
     * @param to    Конец интервала
     */
    <Y extends Comparable<? super Y>> FilterQuery between(String field, Y from, Y to);

    <Y extends Comparable<? super Y>> FilterQuery greaterThan(String field, Y value);

    <Y extends Comparable<? super Y>> FilterQuery lessThan(String field, Y value);

    /**
     * Добавляет в запрос проверку по значению поля
     *
     * @param field Название поля
     * @param value Значение поля для проверки
     */
    FilterQuery matchPhrase(String field, Object value);

    /**
     * Добавляет в запрос проверку по нескольким значениям одного поля
     *
     * @param field Название поля
     * @param value Значения поля
     */
    <U> FilterQuery matchPhrase(String field, Set<U> value);

    /**
     * Добавляет в запрос проверку на NULL значение поля
     *
     * @param field Наименование поля
     */
    FilterQuery exists(String field);

    /**
     * Поиск подстрок
     *
     * @param field Названия поля
     * @param value Часть искомого значения
     */
    FilterQuery like(String field, String value, boolean ignoreCase);

    /**
     * Добавляет в запрос поиск по флагу, который конвертируется в диапазон
     *
     * @param flag  Значение флага
     * @param field Поле для проверки
     */
    FilterQuery checkBoolInt(String field, Boolean flag);

    <Q> Q build();

}
