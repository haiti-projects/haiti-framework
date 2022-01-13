package dev.struchkov.haiti.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * // TODO: 06.09.2020 Добавить описание.
 *
 * @author upagge 06.09.2020
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Assert {

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

    public static void isAnyNotNull(Supplier<? extends RuntimeException> exception, Object... objects) {
        if (Arrays.stream(objects).allMatch(Objects::isNull)) {
            throw exception.get();
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

    @UtilityClass
    public static final class Utils {

        public static Supplier<NullPointerException> nullPointer(String fieldName) {
            return () -> new NullPointerException(fieldName + " is marked non-null but is null");
        }

    }

}
