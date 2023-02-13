package dev.struchkov.haiti.utils;

import java.util.Collection;
import java.util.function.Supplier;

import static dev.struchkov.haiti.utils.Checker.checkBlank;
import static dev.struchkov.haiti.utils.Checker.checkEmpty;
import static dev.struchkov.haiti.utils.Checker.checkNotBlank;
import static dev.struchkov.haiti.utils.Checker.checkNotEmpty;
import static dev.struchkov.haiti.utils.Checker.checkNotNull;
import static dev.struchkov.haiti.utils.Checker.checkNull;
import static dev.struchkov.haiti.utils.Exceptions.utilityClass;

/**
 * Утилитарный класс для различных проверок.
 *
 * @author upagge 06.09.2020
 */
public final class Inspector {

    private Inspector() {
        utilityClass();
    }

    /**
     * Проверка на null значение с возвращением исключения, если объект не null.
     *
     * @param o Проверяемый объект
     * @param e Возвращаемое исключение
     * @throws RuntimeException
     */
    public static void isNull(Object o, Supplier<? extends RuntimeException> e) {
        if (checkNotNull(o)) throw e.get();
    }

    /**
     * Проверяет множество объектов на null. Если хотя бы один объект не null, то будет выброшено исключение
     *
     * @param e       исключение, которое необходимо выбросить
     * @param objects проверяемое множество объектов
     * @throws RuntimeException
     */
    public static void isAnyNotNull(Supplier<? extends RuntimeException> e, Object... objects) {
        for (Object o : objects)
            if (checkNull(o)) throw e.get();
    }

    /**
     * Проверяет множество объектов на null. Если хотя бы один объект null, то будет выброшено исключение
     */
    public static void isAnyNull(Supplier<? extends RuntimeException> e, Object... objects) {
        for (Object o : objects)
            if (checkNotNull(o)) throw e.get();
    }

    /**
     * Проверка на null значение с возвращением исключения, если объект null.
     *
     * @param objects Проверяемый объект
     * @throws NullPointerException
     */
    public static void isNotNull(Object... objects) {
        for (Object o : objects)
            if (checkNull(o)) throw new NullPointerException("Object cannot be null");
    }

    public static void isNotNull(Supplier<? extends RuntimeException> e, Object... objects) {
        for (Object o : objects)
            if (checkNull(o)) throw e.get();
    }

    /**
     * Проверка на true значение с возвращением исключения, если flag не true.
     *
     * @param exception Возвращаемое исключение
     * @throws RuntimeException
     */
    public static void isTrue(boolean flag, Supplier<? extends RuntimeException> exception) {
        if (!flag) throw exception.get();
    }

    /**
     * Проверка на false значение с возвращением исключения, если flag не false.
     *
     * @param exception Возвращаемое исключение
     * @throws RuntimeException
     */
    public static void isFalse(boolean flag, Supplier<? extends RuntimeException> exception) {
        if (flag) throw exception.get();
    }

    /**
     * Проверка коллекции на пустоту. Если коллекция пустая или null, то будет выбрашено исключение.
     *
     * @param exception Возвращаемое исключение
     * @throws RuntimeException
     */
    public static void isNotEmpty(Collection<?> collection, Supplier<? extends RuntimeException> exception) {
        if (checkEmpty(collection)) {
            throw exception.get();
        }
    }

    /**
     * Проверка массива на пустоту. Если массив пустой или null, то будет выбрашено исключение.
     *
     * @param exception Возвращаемое исключение
     * @throws RuntimeException
     */
    public static void isNotEmpty(Supplier<? extends RuntimeException> exception, Object... args) {
        if (checkEmpty(args)) throw exception.get();
    }

    public static void isNotEmpty(String s, Supplier<? extends RuntimeException> exception) {
        if (checkEmpty(s)) throw exception.get();
    }

    /**
     * Проверка коллекции на пустоту. Если коллекция не пустая и не null, то будет выбрашено исключение.
     *
     * @param exception Возвращаемое исключение
     * @throws RuntimeException
     */
    public static void isEmpty(Collection<?> collection, Supplier<? extends RuntimeException> exception) {
        if (checkNotEmpty(collection)) throw exception.get();
    }

    public static void isEmpty(String s, Supplier<? extends RuntimeException> exception) {
        if (checkNotEmpty(s)) throw exception.get();
    }

    /**
     * Проверка массива на пустоту. Если массив не пустой и не null, то будет выбрашено исключение.
     *
     * @param exception Возвращаемое исключение
     * @throws RuntimeException
     */
    public static void isEmpty(Supplier<? extends RuntimeException> exception, Object... args) {
        if (checkNotEmpty(args)) throw exception.get();
    }

    public static void isNotBlank(String s, Supplier<? extends RuntimeException> exception) {
        if (checkBlank(s)) throw exception.get();
    }

    public static void isBlank(String s, Supplier<? extends RuntimeException> exception) {
        if (checkNotBlank(s)) throw exception.get();
    }

    /**
     * Утилитарный класс.
     */
    public static final class Utils {

        private Utils() {
            utilityClass();
        }

        /**
         * Позволяет указать имя поля/параметра, который был null.
         *
         * @param fieldName имя поля/параметра
         * @throws RuntimeException
         */
        public static Supplier<NullPointerException> nullPointer(String fieldName) {
            return () -> new NullPointerException(fieldName + " is marked non-null but is null");
        }

    }

}
