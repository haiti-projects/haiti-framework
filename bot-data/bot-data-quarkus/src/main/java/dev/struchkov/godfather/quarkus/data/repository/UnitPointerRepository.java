package dev.struchkov.godfather.quarkus.data.repository;

import dev.struchkov.godfather.main.domain.UnitPointer;
import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

/**
 * Контракт для сохранения позиции пользователя в сценарии.
 */
public interface UnitPointerRepository {

    Uni<UnitPointer> save(@NotNull UnitPointer unitPointer);

    Uni<String> findUnitNameByPersonId(@NotNull Long personId);

    Uni<Void> removeByPersonId(@NotNull Long personId);

}
