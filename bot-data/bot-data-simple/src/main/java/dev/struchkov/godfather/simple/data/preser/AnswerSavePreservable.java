package dev.struchkov.godfather.simple.data.preser;

import dev.struchkov.godfather.simple.context.service.Pusher;

public interface AnswerSavePreservable<T> extends Preservable<T> {

    /**
     * Финальное сохранение, можно реализовать как отправку данных куда-то
     *
     * @param personId Идентификатор пользователя
     */
    void push(Long personId, Pusher<T> pusher);

}
