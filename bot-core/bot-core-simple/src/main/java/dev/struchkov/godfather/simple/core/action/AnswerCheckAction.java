package dev.struchkov.godfather.simple.core.action;

import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.simple.core.unit.AnswerCheck;
import dev.struchkov.godfather.simple.core.unit.MainUnit;
import dev.struchkov.godfather.simple.core.unit.UnitRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Обработчик Unit-а {@link AnswerCheck}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerCheckAction<M extends Message> implements ActionUnit<AnswerCheck<M>, M> {

    private static final Logger log = LoggerFactory.getLogger(AnswerCheckAction.class);

    @Override
    public UnitRequest<MainUnit, M> action(UnitRequest<AnswerCheck<M>, M> unitRequest) {
        final AnswerCheck<M> unit = unitRequest.getUnit();
        final M message = unitRequest.getMessage();

        MainUnit<M> unitAnswer;
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