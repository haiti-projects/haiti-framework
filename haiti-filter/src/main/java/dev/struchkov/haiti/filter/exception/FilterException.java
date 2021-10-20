package dev.struchkov.haiti.filter.exception;

import dev.struchkov.haiti.context.exception.BasicException;

/**
 * // TODO: 09.11.2020 Добавить описание.
 *
 * @author upagge 09.11.2020
 */
public class FilterException extends BasicException {

    protected FilterException(String message) {
        super(message);
    }

    protected FilterException(String message, Throwable cause) {
        super(message, cause);
    }

}
