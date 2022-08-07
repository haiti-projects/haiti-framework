package dev.struchkov.godfather.main.core.utils;

import dev.struchkov.godfather.main.domain.content.EmptyMessage;
import dev.struchkov.godfather.main.domain.content.Message;

import static dev.struchkov.haiti.utils.Exceptions.utilityClass;

/**
 * Класс для хранения объекта заглушки для {@link Message}.
 *
 * @author upagge [08/07/2019]
 */
public final class MessageUtils {

    public static final EmptyMessage EMPTY_MESSAGE = new EmptyMessage();

    private MessageUtils() {
        utilityClass();
    }

}
