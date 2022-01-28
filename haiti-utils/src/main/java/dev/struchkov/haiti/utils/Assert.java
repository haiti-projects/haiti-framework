package dev.struchkov.haiti.utils;

import java.util.function.Supplier;

import static dev.struchkov.haiti.utils.Exceptions.utilityClass;

/**
 * Утилитарный класс для различных проверок.
 *
 * @author upagge 06.09.2020
 */
public final class Assert {

    private Assert() {
        utilityClass();
    }

    /**
     * Проверка на null значение с возвращением исключения, если объект не null.
     *
     * @param object    Проверяемый объект
     * @param exception Возвращаемое исключение
     */
    public static void isNull(Object object, Supplier<? extends RuntimeException> exception) {
        if (object != null) {
            throw exception.get();
        }
    }

    /**
     * Проверяет множество объектов на null. Если хотябы один объект null, то будет выброшено исключение
     *
     * @param exception исключение, которое необходимо выбросить
     * @param objects   проверяемое множество объектов
     */
    public static void isAnyNotNull(Supplier<? extends RuntimeException> exception, Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                throw exception.get();
            }
        }
    }

    /**
     * Проверка на null значение с возвращением исключения, если объект null.
     *
     * @param object    Проверяемый объект
     * @param exception Возвращаемое исключение
     */
    public static void isNotNull(Object object, Supplier<? extends RuntimeException> exception) {
        if (object == null) {
            throw exception.get();
        }
    }

    /**
     * Проверка на null значение с возвращением исключения, если объект null.
     *
     * @param object Проверяемый объект
     */
    public static void isNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException("Object cannot be null");
        }
    }

    /**
     * Проверка на null значение с возвращением исключения, если объект null.
     *
     * @param objects Проверяемый объект
     */
    public static void isNotNull(Object... objects) {
        for (Object o : objects) {
            if (o == null) {
                throw new NullPointerException("Object cannot be null");
            }
        }
    }

    /**
     * Проверка на true значение с возвращением исключения, если flag не true.
     *
     * @param flag      Проверяемое значение
     * @param exception Возвращаемое исключение
     */
    public static void isTrue(boolean flag, Supplier<? extends RuntimeException> exception) {
        if (!flag) {
            throw exception.get();
        }
    }

    /**
     * Проверка на false значение с возвращением исключения, если flag не false.
     *
     * @param flag      Проверяемое значение
     * @param exception Возвращаемое исключение
     */
    public static void isFalse(boolean flag, Supplier<? extends RuntimeException> exception) {
        if (flag) {
            throw exception.get();
        }
    }


    public static final class Utils {

        public Utils() {
            utilityClass();
        }

        public static Supplier<NullPointerException> nullPointer(String fieldName) {
            return () -> new NullPointerException(fieldName + " is marked non-null but is null");
        }

    }

}
