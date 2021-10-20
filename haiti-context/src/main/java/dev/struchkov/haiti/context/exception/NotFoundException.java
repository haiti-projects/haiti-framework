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

    public static Supplier<NotFoundException> supplier(String message, Object... objects) {
        return () -> new NotFoundException(MessageFormat.format(message, objects));
    }

}
