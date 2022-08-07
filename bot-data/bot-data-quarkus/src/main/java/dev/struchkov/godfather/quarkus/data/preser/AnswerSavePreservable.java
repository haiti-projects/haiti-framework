package dev.struchkov.godfather.quarkus.data.preser;

import dev.struchkov.godfather.quarkus.context.service.Pusher;
import io.smallrye.mutiny.Uni;

public interface AnswerSavePreservable<T> extends Preservable<T> {

    /**
     * Финальное сохранение, можно реализовать как отправку данных куда-то
     *
     * @param personId Идентификатор пользователя
     */
    Uni<Void> push(Long personId, Pusher<T> pusher);

}
