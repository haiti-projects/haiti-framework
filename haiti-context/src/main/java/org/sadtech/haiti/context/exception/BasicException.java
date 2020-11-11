package org.sadtech.haiti.context.exception;

/**
 * Общий класс для всех ошибок приложения.
 *
 * @author upagge 17.12.2019
 */
public abstract class BasicException extends RuntimeException {

    protected BasicException(String message) {
        super(message);
    }

    protected BasicException(String message, Throwable cause) {
        super(message, cause);
    }

}
