package dev.struchkov.godfather.context.domain.content;

import dev.struchkov.godfather.context.exception.AppBotException;
import lombok.ToString;

/**
 * Заглушка для сообщения от пользователя.
 *
 * @author upagge [08/07/2019]
 */
@ToString
public class EmptyMessage extends Message {

    public EmptyMessage() {
        type = ContentType.EMPTY;
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
