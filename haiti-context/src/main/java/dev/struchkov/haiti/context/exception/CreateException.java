package dev.struchkov.haiti.context.exception;

import java.text.MessageFormat;
import java.util.function.Supplier;

/**
 * @author upagge 17.12.2019
 */
public class CreateException extends BasicException {

    public CreateException(String message) {
        super(message);
    }

    public static Supplier<CreateException> supplier(String message, Object... objects) {
        return () -> new CreateException(MessageFormat.format(message, objects));
    }

}
