package dev.struchkov.godfather.simple.core.service;

import dev.struchkov.godfather.main.domain.StorylineHistory;
import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.simple.core.unit.MainUnit;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;

public interface StorylineService<M extends Message> {

    void save(@NotNull StorylineHistory storylineHistory);

    Optional<MainUnit<M>> getUnitNameByPersonId(@NotNull String personId);

    Set<MainUnit<M>> getNextUnitByPersonId(@NotNull String personId);

    void save(String personId, String name, M message);

    Optional<StorylineHistory> replaceUserToBack(String personId, int countUnitsToBack);

    Optional<StorylineHistory> replaceUserToBack(String personId, String unitName);

    Optional<MainUnit<M>> getDefaultUnit();

    /**
     * Ленивая (поздняя) связка юнитов между собой. Осуществляется уже после создания сценария. С помощью данного подхода можно реализовать циклические зависимости юнитов.
     */
    void lazyLink(String firstName, String secondName);

    Optional<MainUnit<M>> getUnitByName(String unitName);

    void setDefaultUnit(String unitName);

}
