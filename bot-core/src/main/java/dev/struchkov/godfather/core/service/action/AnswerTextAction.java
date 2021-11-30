package dev.struchkov.godfather.core.service.action;

import dev.struchkov.godfather.context.domain.BoxAnswer;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.service.sender.Sending;
import dev.struchkov.godfather.context.utils.InsertWords;
import dev.struchkov.godfather.context.utils.Sender;
import dev.struchkov.godfather.core.domain.unit.AnswerText;
import dev.struchkov.godfather.core.domain.unit.MainUnit;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Обработчик Unit-а {@link AnswerText}.
 *
 * @author upagge [11/07/2019]
 */
@AllArgsConstructor
@NoArgsConstructor
public class AnswerTextAction implements ActionUnit<AnswerText, Message> {

    private Sending sending;

    @Override
    public MainUnit action(AnswerText answerText, Message message) {
        BoxAnswer boxAnswer = answerText.getBoxAnswer().toBuilder().build();
        if (answerText.getInsert() != null) {
            List<String> words = answerText.getInsert().insert(message.getPersonId());
            String newMessage = InsertWords.insert(boxAnswer.getMessage(), words);
            boxAnswer.setMessage(newMessage);
        }

        Sending answerTextSending = answerText.getSending();
        if (answerTextSending != null) {
            Sender.sends(message, boxAnswer, answerTextSending);
        } else {
            Sender.sends(message, boxAnswer, this.sending);
        }

        return answerText;
    }


}
