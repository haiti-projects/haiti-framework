package dev.struchkov.godfather.core.service.action;

import dev.struchkov.godfather.context.domain.BoxAnswer;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.service.sender.Sending;
import dev.struchkov.godfather.context.utils.Sender;
import dev.struchkov.godfather.core.domain.unit.AnswerProcessing;
import dev.struchkov.godfather.core.domain.unit.MainUnit;

/**
 * Обработчик Unit-а {@link AnswerProcessing}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerProcessingAction implements ActionUnit<AnswerProcessing<Message>, Message> {

    private final Sending sending;

    public AnswerProcessingAction(Sending sending) {
        this.sending = sending;
    }

    @Override
    public MainUnit action(AnswerProcessing<Message> answerProcessing, Message message) {
        BoxAnswer boxAnswer = answerProcessing.getProcessingData().processing(message);

        Sending answerProcessingSending = answerProcessing.getSending();
        if (answerProcessingSending != null) {
            Sender.sends(message, boxAnswer, answerProcessingSending);
        } else {
            Sender.sends(message, boxAnswer, this.sending);
        }
        return answerProcessing;
    }

}
