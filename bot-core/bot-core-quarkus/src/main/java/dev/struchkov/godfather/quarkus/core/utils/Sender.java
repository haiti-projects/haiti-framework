package dev.struchkov.godfather.quarkus.core.utils;

import dev.struchkov.godfather.main.domain.BoxAnswer;
import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.quarkus.context.service.Sending;
import io.smallrye.mutiny.Uni;

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

    public static Uni<Void> sends(Message message, BoxAnswer boxAnswer, Sending sending) {
        return Uni.createFrom().item(sending)
                .onItem().ifNotNull().transformToUni(
                        sender -> {
                            switch (sending.getType()) {
                                case PUBLIC:
                                    break;
                                case PRIVATE:
                                    return privateSend(message, boxAnswer, sending);
                            }
                            return Uni.createFrom().nullItem();
                        }
                ).replaceWithVoid();
    }

    private static Uni<Void> privateSend(Message message, BoxAnswer boxAnswer, Sending sending) {
        return sending.send(message.getPersonId(), boxAnswer);
    }

}
