package dev.struchkov.godfather.simple.core.action;

import dev.struchkov.godfather.main.core.utils.InsertWords;
import dev.struchkov.godfather.main.domain.BoxAnswer;
import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.simple.context.service.Sending;
import dev.struchkov.godfather.simple.core.unit.AnswerText;
import dev.struchkov.godfather.simple.core.unit.MainUnit;
import dev.struchkov.godfather.simple.core.unit.UnitRequest;
import dev.struchkov.godfather.simple.core.utils.Sender;

import java.util.List;
import java.util.Optional;

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
    public UnitRequest<MainUnit, Message> action(UnitRequest<AnswerText<Message>, Message> unitRequest) {
        final AnswerText<Message> unit = unitRequest.getUnit();
        final Message message = unitRequest.getMessage();

        final Optional<BoxAnswer> optAnswer = unit.getAnswer().processing(message);
        if (optAnswer.isPresent()) {
            final BoxAnswer answer = optAnswer.get();
            replaceMarkers(unit, message, answer);

            final Sending answerTextSending = unit.getSending();
            if (answerTextSending != null) {
                Sender.sends(message, answer, answerTextSending);
            } else {
                Sender.sends(message, answer, this.sending);
            }
        }

        return UnitRequest.of(unit, message);
    }

    private void replaceMarkers(AnswerText<Message> answerText, Message message, BoxAnswer boxAnswer) {
        if (answerText.getInsert() != null) {
            final List<String> words = answerText.getInsert().insert(message.getPersonId());
            final String newMessage = InsertWords.insert(boxAnswer.getMessage(), words);
            boxAnswer.setMessage(newMessage);
        }
    }

}
