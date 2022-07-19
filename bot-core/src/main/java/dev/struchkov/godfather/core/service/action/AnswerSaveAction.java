package dev.struchkov.godfather.core.service.action;

import dev.struchkov.godfather.context.domain.UnitRequest;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.AnswerSave;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import dev.struchkov.godfather.context.repository.preser.AnswerSavePreservable;
import dev.struchkov.godfather.context.service.save.CheckSave;
import dev.struchkov.godfather.context.service.save.PreservableData;

/**
 * Обработчик Unit-а {@link AnswerSave}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerSaveAction<D> implements ActionUnit<AnswerSave<D>, Message> {

    @Override
    public UnitRequest<MainUnit, Message> action(UnitRequest<AnswerSave<D>, Message> unitRequest) {
        final AnswerSave<D> answerSave = unitRequest.getUnit();
        final Message message = unitRequest.getMessage();

        final AnswerSavePreservable<D> preservable = answerSave.getPreservable();
        final Long personId = message.getPersonId();

        final CheckSave<? super Message> checkSave = answerSave.getCheckSave();
        if (checkSave != null) {
            MainUnit unit = checkSave.check(message);
            if (unit != null) {
                return UnitRequest.of(unit, message);
            }
        }

        PreservableData<D, ? super Message> preservableData = answerSave.getPreservableData();
        if (preservableData != null) {
            D data = preservableData.getData(message);
            if (data != null) {
                preservable.save(personId, answerSave.getKey(), data);
            }
        }

        preservable.push(personId, answerSave.getPusher());
        return UnitRequest.of(answerSave, message);
    }
}
