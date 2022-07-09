package dev.struchkov.godfather.context.repository;

import dev.struchkov.godfather.context.domain.StorylineHistory;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface StorylineRepository {

    void save(@NotNull StorylineHistory storylineHistory);

    Optional<StorylineHistory> findByCountLast(long personId, int countUnitsToBack);

    void cleanHistoryByPersonId(@NotNull Long personId);

}
