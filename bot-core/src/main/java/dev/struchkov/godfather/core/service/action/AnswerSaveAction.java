package dev.struchkov.godfather.core.service.action;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.core.domain.unit.AnswerSave;
import dev.struchkov.godfather.core.domain.unit.MainUnit;
import dev.struchkov.godfather.core.service.save.CheckSave;
import dev.struchkov.godfather.core.service.save.Preservable;
import dev.struchkov.godfather.core.service.save.data.PreservableData;

/**
 * Обработчик Unit-а {@link AnswerSave}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerSaveAction<D> implements ActionUnit<AnswerSave<D>, Message> {

    @Override
    public MainUnit action(AnswerSave<D> answerSave, Message mail) {
        Preservable<D> preservable = answerSave.getPreservable();
        Long personId = mail.getPersonId();

        CheckSave<? super Message> checkSave = answerSave.getCheckSave();
        if (checkSave != null) {
            MainUnit unit = checkSave.check(mail);
            if (unit != null) {
                return unit;
            }
        }

        PreservableData<D, ? super Message> preservableData = answerSave.getPreservableData();
        D data = preservableData.getData(mail);
        if (data != null) {
            preservable.save(personId, answerSave.getKey(), data);
        }

        preservable.push(personId, answerSave.getPusher());
        return answerSave;
    }
}
