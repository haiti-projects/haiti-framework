package dev.struchkov.godfather.context.utils;

import dev.struchkov.godfather.context.domain.content.EmptyMessage;
import dev.struchkov.godfather.context.domain.content.Message;

/**
 * Класс для хранения объекта заглушки для {@link Message}.
 *
 * @author upagge [08/07/2019]
 */
public class MessageUtils {

    public static final EmptyMessage EMPTY_MESSAGE = new EmptyMessage();

    private MessageUtils() {
        throw new IllegalStateException(Messages.UTILITY_CLASS);
    }

}
