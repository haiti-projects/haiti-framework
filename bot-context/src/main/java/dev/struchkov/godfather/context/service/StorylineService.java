package dev.struchkov.godfather.context.service;

import dev.struchkov.godfather.context.domain.StorylineHistory;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;

public interface StorylineService<T extends Message> {

    void save(@NotNull StorylineHistory storylineHistory);

    Optional<MainUnit> getUnitNameByPersonId(@NotNull Long personId);

    Set<MainUnit> getNextUnitByPersonId(@NotNull Long personId);

    void save(Long personId, String name, T message);

    Optional<StorylineHistory> replaceUserToBack(long personId, int countUnitsToBack);

    Optional<StorylineHistory> replaceUserToBack(long personId, String unitName);

    Optional<MainUnit> getDefaultUnit();

    /**
     * Ленивая (поздняя) связка юнитов между собой. Осуществляется уже после создания сценария. С помощью данного подхода можно реализовать циклические зависимости юнитов. Либо можно использовать {@link dev.struchkov.godfather.context.domain.unit.cmd.TeleportCmd}
     */
    void lazyLink(String firstName, String secondName);

    Optional<MainUnit> getUnitByName(String unitName);

}
