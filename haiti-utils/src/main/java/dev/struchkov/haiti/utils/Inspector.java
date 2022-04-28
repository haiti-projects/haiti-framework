package dev.struchkov.haiti.utils;

import java.util.Collection;
import java.util.function.Supplier;

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
     * @param object    Проверяемый объект
     * @param exception Возвращаемое исключение
     * @throws RuntimeException
     */
    public static void isNull(Object object, Supplier<? extends RuntimeException> exception) {
        if (object != null) throw exception.get();
    }

    /**
     * Проверяет множество объектов на null. Если хотябы один объект null, то будет выброшено исключение
     *
     * @param exception исключение, которое необходимо выбросить
     * @param objects   проверяемое множество объектов
     * @throws RuntimeException
     */
    public static void isAnyNotNull(Supplier<? extends RuntimeException> exception, Object... objects) {
        for (Object object : objects)
            if (object == null) throw exception.get();
    }

    /**
     * Проверка на null значение с возвращением исключения, если объект null.
     *
     * @param object    Проверяемый объект
     * @param exception Возвращаемое исключение
     */
    public static void isNotNull(Object object, Supplier<? extends RuntimeException> exception) {
        if (object == null) throw exception.get();
    }

    /**
     * Проверка на null значение с возвращением исключения, если объект null.
     *
     * @param object Проверяемый объект
     * @throws NullPointerException
     */
    public static void isNotNull(Object object) {
        if (object == null) throw new NullPointerException("Object cannot be null");
    }

    /**
     * Проверка на null значение с возвращением исключения, если объект null.
     *
     * @param objects Проверяемый объект
     * @throws NullPointerException
     */
    public static void isNotNull(Object... objects) {
        for (Object o : objects)
            if (o == null) throw new NullPointerException("Object cannot be null");
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
     * Проверка коллекции на пустоту.
     *
     * @return false - если коллекция null или пустая, true в противном случае.
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * Проверка массива на пустоту
     *
     * @return false - если массив null или пустой, true в противном случае.
     */
    public static boolean isNotEmpty(Object... args) {
        if (args == null) {
            return false;
        } else {
            return args.length > 0;
        }
    }

    /**
     * Проверка коллекции на пустоту.
     *
     * @return true - если коллекция null или пустая, false в противном случае
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Проверка массива на пустоту.
     *
     * @return true - если массив null или пустой, false в противном случае
     */
    public static boolean isEmpty(Object... args) {
        return args == null || args.length == 0;
    }

    /**
     * Проверка коллекции на пустоту. Если коллекция пустая или null, то будет выбрашено исключение.
     *
     * @param exception Возвращаемое исключение
     * @throws RuntimeException
     */
    public static void isNotEmpty(Collection<?> collection, Supplier<? extends RuntimeException> exception) {
        if (isEmpty(collection)) {
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
        if (isEmpty(args)) {
            throw exception.get();
        }
    }

    /**
     * Проверка коллекции на пустоту. Если коллекция не пустая и не null, то будет выбрашено исключение.
     *
     * @param exception Возвращаемое исключение
     * @throws RuntimeException
     */
    public static void isEmpty(Collection<?> collection, Supplier<? extends RuntimeException> exception) {
        if (isNotEmpty(collection)) throw exception.get();
    }

    /**
     * Проверка массива на пустоту. Если массив не пустой и не null, то будет выбрашено исключение.
     *
     * @param exception Возвращаемое исключение
     * @throws RuntimeException
     */
    public static void isEmpty(Supplier<? extends RuntimeException> exception, Object... args) {
        if (isNotEmpty(args)) throw exception.get();
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
