package dev.struchkov.godfather.core.service.action;

import dev.struchkov.godfather.context.domain.UnitRequest;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.MainUnit;

/**
 * Интерфейс для обработки Unit-ов.
 *
 * @author upagge [11/07/2019]
 */
@FunctionalInterface
public interface ActionUnit<U extends MainUnit, M extends Message> {

    /**
     * Метод обработки Unit-а.
     *
     * @return Новый Unit, который может нуждаться в обработке
     */
    UnitRequest<MainUnit, M> action(UnitRequest<U, M> unitRequest);

}
