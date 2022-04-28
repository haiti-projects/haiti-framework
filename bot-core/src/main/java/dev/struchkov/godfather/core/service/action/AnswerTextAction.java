package dev.struchkov.godfather.core.service.action;

import dev.struchkov.godfather.context.domain.BoxAnswer;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.service.sender.Sending;
import dev.struchkov.godfather.context.utils.InsertWords;
import dev.struchkov.godfather.context.utils.Sender;
import dev.struchkov.godfather.core.domain.unit.AnswerText;
import dev.struchkov.godfather.core.domain.unit.MainUnit;

import java.util.List;

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
    public MainUnit action(AnswerText<Message> answerText, Message message) {
        final BoxAnswer boxAnswer = answerText.getBoxAnswer().processing(message);
        replaceMarkers(answerText, message, boxAnswer);

        final Sending answerTextSending = answerText.getSending();
        if (answerTextSending != null) {
            Sender.sends(message, boxAnswer, answerTextSending);
        } else {
            Sender.sends(message, boxAnswer, this.sending);
        }

        return answerText;
    }

    private void replaceMarkers(AnswerText<Message> answerText, Message message, BoxAnswer boxAnswer) {
        if (answerText.getInsert() != null) {
            final List<String> words = answerText.getInsert().insert(message.getPersonId());
            final String newMessage = InsertWords.insert(boxAnswer.getMessage(), words);
            boxAnswer.setMessage(newMessage);
        }
    }


}
