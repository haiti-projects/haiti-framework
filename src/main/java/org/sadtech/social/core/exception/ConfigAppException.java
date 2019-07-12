package org.sadtech.social.core.exception;

/**
 * Исключения настройки бота.
 *
 * @author upagge [11/07/2019]
 */
public class ConfigAppException extends AppBotException {
    public ConfigAppException(Integer code, String message) {
        super(code, message);
    }
}
