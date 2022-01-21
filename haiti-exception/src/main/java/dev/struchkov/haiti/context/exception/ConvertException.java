package dev.struchkov.haiti.context.exception;

import java.text.MessageFormat;
import java.util.function.Supplier;

/**
 * Исключение при конвертации.
 */
public class ConvertException extends BasicException {

    public ConvertException(String message) {
        super(message);
    }

    public ConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public static Supplier<ConvertException> supplier(String message, Object... objects) {
        return () -> new ConvertException(MessageFormat.format(message, objects));
    }

}
