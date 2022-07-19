package dev.struchkov.godfather.context.repository.preser;

import dev.struchkov.godfather.context.service.save.Pusher;

public interface AnswerSavePreservable<T> extends Preservable<T> {

    /**
     * Финальное сохранение, можно реализовать как отправку данных куда-то
     *
     * @param personId Идентификатор пользователя
     */
    void push(Long personId, Pusher<T> pusher);

}
