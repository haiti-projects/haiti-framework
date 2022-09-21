package dev.struchkov.godfather.quarkus.core.action;

import dev.struchkov.godfather.main.core.utils.InsertWords;
import dev.struchkov.godfather.main.domain.BoxAnswer;
import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.quarkus.context.service.Sending;
import dev.struchkov.godfather.quarkus.core.unit.AnswerText;
import dev.struchkov.godfather.quarkus.core.unit.MainUnit;
import dev.struchkov.godfather.quarkus.core.unit.UnitRequest;
import dev.struchkov.godfather.quarkus.core.utils.Sender;
import io.smallrye.mutiny.Uni;

import static dev.struchkov.haiti.utils.Checker.checkNotEmpty;
import static dev.struchkov.haiti.utils.Checker.checkNotNull;

/**
 * Обработчик Unit-а {@link AnswerText}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerTextAction implements ActionUnit<AnswerText<Message>, Message> {

    private final Sending sending;

    public AnswerTextAction(Sending sending) {
        this.sending = sending;
    }

    @Override
    public Uni<UnitRequest<MainUnit, Message>> action(UnitRequest<AnswerText<Message>, Message> unitRequest) {
        final AnswerText<Message> unit = unitRequest.getUnit();
        final Message message = unitRequest.getMessage();

        return unit.getAnswer().processing(message)
                .onItem().transformToUni(
                        boxAnswer -> {
                            if (checkNotNull(boxAnswer)) {
                                return replaceMarkers(unit, message, boxAnswer);
                            }
                            return Uni.createFrom().nullItem();
                        }
                ).onItem().transformToUni(
                        boxAnswer -> {
                            if (checkNotNull(boxAnswer)) {
                                final Sending answerTextSending = unit.getSending();
                                if (answerTextSending != null) {
                                    return Sender.sends(message, boxAnswer, answerTextSending);
                                } else {
                                    return Sender.sends(message, boxAnswer, this.sending);
                                }
                            }
                            return Uni.createFrom().nullItem();
                        }
                ).onItem().transform(
                        v -> UnitRequest.of(unit, message)
                );
    }

    private Uni<BoxAnswer> replaceMarkers(AnswerText<Message> answerText, Message message, BoxAnswer boxAnswer) {
        if (answerText.getInsert() != null) {
            return answerText.getInsert().insert(message.getPersonId())
                    .onItem().transformToUni(
                            words -> {
                                if (checkNotEmpty(words)) {
                                    final String newMessage = InsertWords.insert(boxAnswer.getMessage(), words);
                                    boxAnswer.setMessage(newMessage);
                                }
                                return Uni.createFrom().item(boxAnswer);
                            }
                    );
        }
        return Uni.createFrom().item(boxAnswer);
    }

}
