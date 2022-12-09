package dev.struchkov.godfather.quarkus.core.service;

import dev.struchkov.godfather.main.domain.StorylineHistory;
import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.quarkus.core.unit.MainUnit;
import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;

public interface StorylineService<M extends Message> {

    Uni<Void> save(@NotNull StorylineHistory storylineHistory);

    Uni<MainUnit<M>> getUnitNameByPersonId(@NotNull String personId);

    Uni<Set<MainUnit<M>>> getNextUnitByPersonId(@NotNull String personId);

    Uni<Void> save(String personId, String name, M message);

    Uni<StorylineHistory> replaceUserToBack(String personId, int countUnitsToBack);

    Uni<StorylineHistory> replaceUserToBack(String personId, String unitName);

    Optional<MainUnit<M>> getDefaultUnit();

    /**
     * Ленивая (поздняя) связка юнитов между собой. Осуществляется уже после создания сценария. С помощью данного подхода можно реализовать циклические зависимости юнитов.
     */
    void lazyLink(String firstName, String secondName);

    Optional<MainUnit<M>> getUnitByName(String unitName);

    void setDefaultUnit(String unitName);

}
