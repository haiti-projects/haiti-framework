package dev.struchkov.godfather.core.service.action;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.core.domain.unit.AnswerCheck;
import dev.struchkov.godfather.core.domain.unit.MainUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Обработчик Unit-а {@link AnswerCheck}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerCheckAction implements ActionUnit<AnswerCheck, Message> {

    private static final Logger log = LoggerFactory.getLogger(AnswerCheckAction.class);

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
