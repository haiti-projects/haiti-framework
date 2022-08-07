package dev.struchkov.godfather.exception;

/**
 * Ошибка доступа к чему-либо.
 *
 * @author upagge [08/07/2019]
 */
public class AccessException extends AppBotException {

    public AccessException(String message) {
        super(message);
    }

}
