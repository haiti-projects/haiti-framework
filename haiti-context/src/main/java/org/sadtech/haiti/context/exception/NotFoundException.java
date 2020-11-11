package org.sadtech.haiti.context.exception;

/**
 * Исключения, возникающиек, когда необходимые данные не были найдены.
 *
 * @author upagge 17.12.2019
 */
public class NotFoundException extends BasicException {

    public NotFoundException(String message) {
        super(message);
    }

}
