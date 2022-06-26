package dev.struchkov.godfather.context.repository;

import dev.struchkov.godfather.context.domain.UnitPointer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Контракт для сохранения позиции пользователя в сценарии.
 */
public interface UnitPointerRepository {

    UnitPointer save(@NotNull UnitPointer unitPointer);

    Optional<String> findUnitNameByPersonId(@NotNull Long personId);

    void removeByPersonId(@NotNull Long personId);

}
