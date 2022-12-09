package dev.struchkov.godfather.simple.data.repository;

import dev.struchkov.godfather.main.domain.UnitPointer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Контракт для сохранения позиции пользователя в сценарии.
 */
public interface UnitPointerRepository {

    UnitPointer save(@NotNull UnitPointer unitPointer);

    Optional<String> findUnitNameByPersonId(@NotNull String personId);

    void removeByPersonId(@NotNull String personId);

}
