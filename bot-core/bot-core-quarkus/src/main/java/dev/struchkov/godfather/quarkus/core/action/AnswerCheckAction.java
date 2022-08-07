package dev.struchkov.godfather.quarkus.core.action;

import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.quarkus.core.unit.AnswerCheck;
import dev.struchkov.godfather.quarkus.core.unit.MainUnit;
import dev.struchkov.godfather.quarkus.core.unit.UnitRequest;
import io.smallrye.mutiny.Uni;

import java.util.Objects;

import static dev.struchkov.haiti.utils.Checker.checkNotNull;

/**
 * Обработчик Unit-а {@link AnswerCheck}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerCheckAction<M extends Message> implements ActionUnit<AnswerCheck<M>, M> {

    @Override
    public Uni<UnitRequest<MainUnit, M>> action(UnitRequest<AnswerCheck<M>, M> unitRequest) {
        final AnswerCheck<M> unit = unitRequest.getUnit();
        final M message = unitRequest.getMessage();

        return unit.getCheck().checked(message)
                .onItem().transform(
                        checkValue -> {
                            if (checkNotNull(checkValue) && checkValue) {
                                return UnitRequest.of(Objects.requireNonNullElse(unit.getUnitTrue(), unit), message);
                            } else {
                                return UnitRequest.of(Objects.requireNonNullElse(unit.getUnitFalse(), unit), message);
                            }
                        }
                );
    }
}
