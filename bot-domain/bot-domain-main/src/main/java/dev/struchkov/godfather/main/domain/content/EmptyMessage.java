package dev.struchkov.godfather.main.domain.content;

import dev.struchkov.godfather.exception.AppBotException;

/**
 * Заглушка для сообщения от пользователя.
 *
 * @author upagge [08/07/2019]
 */
public class EmptyMessage extends Message {

    public EmptyMessage() {
        contentType = ContentType.EMPTY;
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public void setText(String text) {
        throw new AppBotException("EmptyMessage no setText");
    }

}
