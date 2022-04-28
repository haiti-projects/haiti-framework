package dev.struchkov.godfather.context.utils;

import dev.struchkov.godfather.context.domain.BoxAnswer;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.service.sender.Sending;

import static dev.struchkov.haiti.utils.Exceptions.utilityClass;

/**
 * Используется для отправки сообщений определенного типа.
 *
 * @author upagge
 */
public class Sender {

    private Sender() {
        utilityClass();
    }

    public static void sends(Message message, BoxAnswer boxAnswer, Sending sending) {
        switch (sending.getType()) {
            case PUBLIC:
                break;
            case PRIVATE:
                privateSend(message, boxAnswer, sending);
                break;
        }
    }

    private static void privateSend(Message message, BoxAnswer boxAnswer, Sending sending) {
        sending.send(message.getPersonId(), boxAnswer);
    }

}
