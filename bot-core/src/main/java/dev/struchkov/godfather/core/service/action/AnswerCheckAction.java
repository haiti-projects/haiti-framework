package dev.struchkov.godfather.core.service.action;

import dev.struchkov.godfather.context.domain.UnitRequest;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.AnswerCheck;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;

/**
 * Обработчик Unit-а {@link AnswerCheck}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerCheckAction implements ActionUnit<AnswerCheck, Message> {

    private static final Logger log = LoggerFactory.getLogger(AnswerCheckAction.class);

    @Override
    public UnitRequest<MainUnit, Message> action(UnitRequest<AnswerCheck, Message> unitRequest) {
        final AnswerCheck unit = unitRequest.getUnit();
        final Message message = unitRequest.getMessage();

        MainUnit unitAnswer;
        if (unit.getCheck().checked(message)) {
            log.info("Проверка пройдена");
            unitAnswer = unit.getUnitTrue();
        } else {
            log.info("Проверка не пройдена");
            unitAnswer = unit.getUnitFalse();
        }
        return UnitRequest.of(Objects.requireNonNullElse(unitAnswer, unit), message);
    }
}
