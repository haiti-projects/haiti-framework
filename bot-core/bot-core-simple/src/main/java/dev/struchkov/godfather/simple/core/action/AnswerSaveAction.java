package dev.struchkov.godfather.simple.core.action;

import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.simple.context.service.Pusher;
import dev.struchkov.godfather.simple.core.unit.AnswerSave;
import dev.struchkov.godfather.simple.core.unit.MainUnit;
import dev.struchkov.godfather.simple.core.unit.UnitRequest;
import dev.struchkov.godfather.simple.core.unit.func.CheckSave;
import dev.struchkov.godfather.simple.core.unit.func.PreservableData;
import dev.struchkov.godfather.simple.data.preser.AnswerSavePreservable;

import static dev.struchkov.haiti.utils.Checker.checkNotNull;

/**
 * Обработчик Unit-а {@link AnswerSave}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerSaveAction<M extends Message, D> implements ActionUnit<AnswerSave<M, D>, M> {

    @Override
    public UnitRequest<MainUnit, M> action(UnitRequest<AnswerSave<M, D>, M> unitRequest) {
        final AnswerSave<M, D> answerSave = unitRequest.getUnit();
        final M message = unitRequest.getMessage();

        final AnswerSavePreservable<D> preservable = answerSave.getPreservable();
        final String personId = message.getPersonId();

        final CheckSave<M> checkSave = answerSave.getCheckSave();
        if (checkNotNull(checkSave)) {
            MainUnit<M> unit = checkSave.check(message);
            if (checkNotNull(unit)) {
                return UnitRequest.of(unit, message);
            }
        }

        final PreservableData<D, M> preservableData = answerSave.getPreservableData();
        if (checkNotNull(preservableData)) {
            D data = preservableData.getData(message);
            if (checkNotNull(data)) {
                preservable.save(personId, answerSave.getKey(), data);
            }
        }

        final Pusher<D> pusher = answerSave.getPusher();
        if (checkNotNull(pusher)) {
            preservable.push(personId, pusher);
        }
        return UnitRequest.of(answerSave, message);
    }
}
