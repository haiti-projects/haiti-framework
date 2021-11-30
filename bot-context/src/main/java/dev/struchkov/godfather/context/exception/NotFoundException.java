package dev.struchkov.godfather.context.exception;

/**
 * Ошибка, когда что-то не найдено.
 *
 * @author upagge [08/07/2019]
 */
public class NotFoundException extends AppBotException {

    public NotFoundException(String message) {
        super(message);
    }

}
