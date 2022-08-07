package dev.struchkov.godfather.exception;

import java.util.function.Supplier;

/**
 * Ошибки связанные с юнитом RollBackCmd
 */
public class RollBackException extends AppBotException {

    public RollBackException(String message) {
        super(message);
    }

    public static Supplier<RollBackException> rollBackException(String message) {
        return () -> new RollBackException(message);
    }

}
