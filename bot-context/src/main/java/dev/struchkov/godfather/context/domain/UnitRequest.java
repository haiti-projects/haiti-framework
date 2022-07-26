package dev.struchkov.godfather.context.domain;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.MainUnit;

/**
 * Сущность инкапсулирует в себе данные, необходимые для обработки сценария.
 *
 * @param <U> Тип юнита
 * @param <M> Тип сообщения
 */
public class UnitRequest<U extends MainUnit, M extends Message> {

    private final U unit;
    private final M message;

    private UnitRequest(U unit, M message) {
        this.unit = unit;
        this.message = message;
    }

    public static <U extends MainUnit<M>, M extends Message> UnitRequest<U, M> of(U mainUnit, M message) {
        return new UnitRequest<>(mainUnit, message);
    }

    public U getUnit() {
        return unit;
    }

    public M getMessage() {
        return message;
    }

}
