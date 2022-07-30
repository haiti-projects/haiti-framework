package dev.struchkov.haiti.context.exception;

import java.text.MessageFormat;
import java.util.function.Supplier;

/**
 * Исключения связанные с доступом.
 */
public class AccessException extends BasicException {

    public AccessException(String message) {
        super(message);
    }

    public static Supplier<AccessException> accessException(String message, Object... objects) {
        return () -> new AccessException(MessageFormat.format(message, objects));
    }

}

