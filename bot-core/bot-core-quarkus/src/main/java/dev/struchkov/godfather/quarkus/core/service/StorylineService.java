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

    Uni<MainUnit<M>> getUnitNameByPersonId(@NotNull Long personId);

    Uni<Set<MainUnit<M>>> getNextUnitByPersonId(@NotNull Long personId);

    Uni<Void> save(Long personId, String name, M message);

    Uni<StorylineHistory> replaceUserToBack(long personId, int countUnitsToBack);

    Uni<StorylineHistory> replaceUserToBack(long personId, String unitName);

    Optional<MainUnit<M>> getDefaultUnit();

    /**
     * Ленивая (поздняя) связка юнитов между собой. Осуществляется уже после создания сценария. С помощью данного подхода можно реализовать циклические зависимости юнитов.
     */
    void lazyLink(String firstName, String secondName);

    Optional<MainUnit<M>> getUnitByName(String unitName);

    void setDefaultUnit(String unitName);

}
