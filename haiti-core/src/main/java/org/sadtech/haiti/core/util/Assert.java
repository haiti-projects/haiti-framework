package org.sadtech.haiti.core.util;

/**
 * // TODO: 06.09.2020 Добавить описание.
 *
 * @author upagge 06.09.2020
 */

public final class Assert {

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalStateException(message);
        }
    }

    public static void isNotNull(Object object, String message) {
        if (object == null) {
            throw new IllegalStateException(message);
        }
    }

}
