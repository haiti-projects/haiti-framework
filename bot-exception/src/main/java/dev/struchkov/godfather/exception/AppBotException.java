package dev.struchkov.godfather.exception;

import java.time.LocalDateTime;

/**
 * Родитель всех исключений библиотеки.
 *
 * @author upagge [08/07/2019]
 */
public class AppBotException extends RuntimeException {

    private static final String TYPE = "ERROR";
    private final LocalDateTime timeError = LocalDateTime.now();

    public AppBotException(String message) {
        super(message);
    }

    public String getType() {
        return TYPE;
    }

    public LocalDateTime getTimeError() {
        return timeError;
    }

}
