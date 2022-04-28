package dev.struchkov.haiti.context.exception;

import java.text.MessageFormat;
import java.util.function.Supplier;

/**
 * Исключения, возникающие, когда необходимые данные не были найдены.
 *
 * @author upagge 17.12.2019
 */
public class NotFoundException extends BasicException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }

    public static Supplier<NotFoundException> notFoundException(String message, Object... args) {
        return () -> new NotFoundException(message, args);
    }

}
