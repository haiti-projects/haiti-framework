package dev.struchkov.godfather.context.service;

import dev.struchkov.godfather.context.domain.UnitPointer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Сервис для взаимодействия с сущностью {@link UnitPointer}.
 *
 * @author upagge [07/07/2019]
 */
public interface UnitPointerService {

    UnitPointer save(@NotNull UnitPointer unitPointer);

    Optional<String> getUnitNameByPersonId(@NotNull Long personId);

    void removeByPersonId(@NotNull Long personId);

}
