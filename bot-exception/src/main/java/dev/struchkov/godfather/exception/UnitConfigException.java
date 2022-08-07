package dev.struchkov.godfather.exception;

import java.text.MessageFormat;
import java.util.function.Supplier;

/**
 * Иключения связанные с настройками юнитов.
 *
 * @author upagge 28.04.2022
 */
public class UnitConfigException extends AppBotException {

    public UnitConfigException(String message) {
        super(message);
    }

    public UnitConfigException(String message, Object... objects) {
        super(MessageFormat.format(message, objects));
    }

    public static Supplier<UnitConfigException> unitConfigException(String message, Object... objects) {
        return () -> new UnitConfigException(message, objects);
    }

}
