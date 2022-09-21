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
        final Message message = unitRequest.getMessage();
        final AnswerText<Message> unit = unitRequest.getUnit();
        return Uni.createFrom().voidItem()
                .chain(request -> unit.getAnswer().processing(message))
                .onItem().ifNotNull().transformToUni(boxAnswer -> replaceMarkers(unit, message, boxAnswer))
                .onItem().ifNotNull().transformToUni(boxAnswer -> {
                    final Sending answerTextSending = unit.getSending();
                    if (checkNotNull(answerTextSending)) {
                        return Sender.sends(message, boxAnswer, answerTextSending);
                    } else {
                        return Sender.sends(message, boxAnswer, this.sending);
                    }
                }).replaceWith(UnitRequest.of(unit, message));
    }

    private Uni<BoxAnswer> replaceMarkers(AnswerText<Message> answerText, Message message, BoxAnswer boxAnswer) {
        return Uni.createFrom().item(answerText.getInsert())
                .onItem().ifNotNull().transformToUni(insert -> insert.insert(message.getPersonId()))
                .onItem().ifNotNull().transform(words -> {
                    if (checkNotEmpty(words)) {
                        final String newMessage = InsertWords.insert(boxAnswer.getMessage(), words);
                        boxAnswer.setMessage(newMessage);
                    }
                    return boxAnswer;
                })
                .replaceIfNullWith(boxAnswer);
    }

}
