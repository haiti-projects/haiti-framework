package dev.struchkov.godfather.simple.core.action;

import dev.struchkov.godfather.main.domain.BoxAnswer;
import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.simple.context.service.Sending;
import dev.struchkov.godfather.simple.core.unit.AnswerCheck;
import dev.struchkov.godfather.simple.core.unit.MainUnit;
import dev.struchkov.godfather.simple.core.unit.UnitRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static dev.struchkov.haiti.utils.Checker.checkNotNull;

/**
 * Обработчик Unit-а {@link AnswerCheck}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerCheckAction<M extends Message> implements ActionUnit<AnswerCheck<M>, M> {

    private static final Logger log = LoggerFactory.getLogger(AnswerCheckAction.class);

    private final Sending sending;

    public AnswerCheckAction(Sending sending) {
        this.sending = sending;
    }

    @Override
    public UnitRequest<MainUnit, M> action(UnitRequest<AnswerCheck<M>, M> unitRequest) {
        final AnswerCheck<M> unit = unitRequest.getUnit();
        log.debug("Началась обработка unit: {}.", unit.getName());

        final M message = unitRequest.getMessage();

        MainUnit<M> unitAnswer;
        if (unit.getCheck().checked(message)) {
            log.debug("Unit: {}. Проверка пройдена", unit.getName());
            final BoxAnswer answerIfTrue = unit.getIntermediateAnswerIfTrue();
            if (checkNotNull(answerIfTrue)) {
                answerIfTrue.setRecipientIfNull(message.getPersonId());
                sending.send(answerIfTrue);
            }
            unitAnswer = unit.getUnitTrue();
        } else {
            log.debug("Unit: {}. Проверка НЕ пройдена", unit.getName());
            final BoxAnswer answerIfFalse = unit.getIntermediateAnswerIfFalse();
            if (checkNotNull(answerIfFalse)) {
                answerIfFalse.setRecipientIfNull(message.getPersonId());
                sending.send(answerIfFalse);
            }
            unitAnswer = unit.getUnitFalse();
        }
        log.debug("Завершилась обработка unit: {}.", unit.getName());
        return UnitRequest.of(Objects.requireNonNullElse(unitAnswer, unit), message);
    }
}
