package org.sadtech.haiti.context.exception;

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

}
