package dev.struchkov.godfather.quarkus.core.action;

import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.quarkus.core.unit.MainUnit;
import dev.struchkov.godfather.quarkus.core.unit.UnitRequest;
import io.smallrye.mutiny.Uni;

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
    Uni<UnitRequest<MainUnit, M>> action(UnitRequest<U, M> unitRequest);

}
