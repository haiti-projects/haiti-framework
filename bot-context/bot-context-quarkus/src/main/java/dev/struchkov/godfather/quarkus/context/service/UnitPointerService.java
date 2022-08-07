package dev.struchkov.godfather.quarkus.context.service;

import dev.struchkov.godfather.main.domain.UnitPointer;
import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

/**
 * Сервис для взаимодействия с сущностью {@link UnitPointer}.
 *
 * @author upagge [07/07/2019]
 */
public interface UnitPointerService {

    Uni<UnitPointer> save(@NotNull UnitPointer unitPointer);

    Uni<String> getUnitNameByPersonId(@NotNull Long personId);

    Uni<Void> removeByPersonId(@NotNull Long personId);

}
