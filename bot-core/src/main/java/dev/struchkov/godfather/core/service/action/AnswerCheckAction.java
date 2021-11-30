package dev.struchkov.godfather.core.service.action;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.core.domain.unit.AnswerCheck;
import dev.struchkov.godfather.core.domain.unit.MainUnit;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * Обработчик Unit-а {@link AnswerCheck}.
 *
 * @author upagge [11/07/2019]
 */
@Slf4j
public class AnswerCheckAction implements ActionUnit<AnswerCheck, Message> {

    @Override
    public MainUnit action(AnswerCheck answerCheck, Message mail) {
        MainUnit unitAnswer;
        if (answerCheck.getCheck().checked(mail)) {
            log.info("Проверка пройдена");
            unitAnswer = answerCheck.getUnitTrue();
        } else {
            log.info("Проверка не пройдена");
            unitAnswer = answerCheck.getUnitFalse();
        }
        return Optional.ofNullable(unitAnswer).orElse(answerCheck);
    }

}
