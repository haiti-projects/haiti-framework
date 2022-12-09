package dev.struchkov.godfather.simple.data.repository;

import dev.struchkov.godfather.main.domain.StorylineHistory;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface StorylineRepository {

    void save(@NotNull StorylineHistory storylineHistory);

    Optional<StorylineHistory> findByCountLast(String personId, int countUnitsToBack);

    Optional<StorylineHistory> findByCountLast(String personId, String unitName);

    void cleanHistoryByPersonId(@NotNull String personId);

}
