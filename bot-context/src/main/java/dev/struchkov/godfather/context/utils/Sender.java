package dev.struchkov.godfather.context.utils;

import dev.struchkov.godfather.context.domain.BoxAnswer;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.service.sender.Sending;
import dev.struchkov.godfather.context.domain.content.Comment;

/**
 * Используется для отправки сообщений определенного типа.
 *
 * @author upagge
 */
public class Sender {

    private Sender() {
        throw new IllegalStateException(Messages.UTILITY_CLASS);
    }

    public static void sends(Message message, BoxAnswer boxAnswer, Sending sending) {
        switch (sending.getType()) {
            case PUBLIC:
                publicSend(message, boxAnswer, sending);
                break;
            case PRIVATE:
                privateSend(message, boxAnswer, sending);
                break;
        }
    }

    private static void publicSend(Message message, BoxAnswer boxAnswer, Sending sending) {
        if (message instanceof Comment) {
            sending.send(((Comment) message).getContentId(), message.getPersonId(), boxAnswer);
        }
    }

    private static void privateSend(Message message, BoxAnswer boxAnswer, Sending sending) {
        sending.send(message.getPersonId(), boxAnswer);
    }

}
