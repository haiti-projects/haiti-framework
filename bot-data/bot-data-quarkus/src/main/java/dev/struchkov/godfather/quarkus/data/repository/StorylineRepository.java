package dev.struchkov.godfather.quarkus.data.repository;

import dev.struchkov.godfather.main.domain.StorylineHistory;
import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

public interface StorylineRepository {

    Uni<Void> save(@NotNull StorylineHistory storylineHistory);

    Uni<StorylineHistory> findByCountLast(String personId, int countUnitsToBack);

    Uni<StorylineHistory> findByCountLast(String personId, String unitName);

    Uni<Void> cleanHistoryByPersonId(@NotNull String personId);

}
