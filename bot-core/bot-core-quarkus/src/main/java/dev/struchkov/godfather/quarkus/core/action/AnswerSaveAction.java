package dev.struchkov.godfather.quarkus.core.action;

import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.quarkus.context.service.Pusher;
import dev.struchkov.godfather.quarkus.core.unit.AnswerSave;
import dev.struchkov.godfather.quarkus.core.unit.MainUnit;
import dev.struchkov.godfather.quarkus.core.unit.UnitRequest;
import dev.struchkov.godfather.quarkus.core.unit.func.CheckSave;
import dev.struchkov.godfather.quarkus.core.unit.func.PreservableData;
import dev.struchkov.godfather.quarkus.data.preser.AnswerSavePreservable;
import io.smallrye.mutiny.Uni;

import static dev.struchkov.haiti.utils.Checker.checkNotNull;

/**
 * Обработчик Unit-а {@link AnswerSave}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerSaveAction<M extends Message, D> implements ActionUnit<AnswerSave<M, D>, M> {

    @Override
    public Uni<UnitRequest<MainUnit, M>> action(UnitRequest<AnswerSave<M, D>, M> unitRequest) {
        final AnswerSave<M, D> answerSave = unitRequest.getUnit();
        final M message = unitRequest.getMessage();

        final AnswerSavePreservable<D> preservable = answerSave.getPreservable();
        final String personId = message.getPersonId();

        final CheckSave<M> checkSave = answerSave.getCheckSave();
        if (checkNotNull(checkSave)) {
            return Uni.createFrom().voidItem()
                    .onItem().transformToUni(
                            v -> checkSave.check(message)
                                    .onItem().transform(
                                            unit -> {
                                                if (checkNotNull(unit)) {
                                                    return UnitRequest.of(unit, message);
                                                }
                                                return UnitRequest.of(answerSave, message);
                                            }
                                    )
                    );
        }

        final PreservableData<D, M> preservableData = answerSave.getPreservableData();
        final Pusher<D> pusher = answerSave.getPusher();

        return Uni.createFrom().voidItem()
                .onItem().transformToUni(
                        v -> {
                            if (checkNotNull(preservableData)) {
                                return preservableData.getData(message);
                            }
                            return Uni.createFrom().nullItem();
                        }
                ).onItem().transformToUni(
                        dataFromSave -> {
                            if (checkNotNull(dataFromSave)) {
                                return preservable.save(personId, answerSave.getKey(), dataFromSave);
                            }
                            return Uni.createFrom().nullItem();
                        }
                ).onItem().transformToUni(
                        v -> {
                            if (checkNotNull(pusher)) {
                                return preservable.push(personId, pusher);
                            }
                            return Uni.createFrom().nullItem();
                        }
                ).onItem().transform(
                        v -> UnitRequest.of(answerSave, message)
                );
    }
}
