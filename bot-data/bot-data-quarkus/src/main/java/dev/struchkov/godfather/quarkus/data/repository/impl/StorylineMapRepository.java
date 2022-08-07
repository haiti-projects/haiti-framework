package dev.struchkov.godfather.quarkus.data.repository.impl;

import dev.struchkov.godfather.main.domain.StorylineHistory;
import dev.struchkov.godfather.quarkus.data.repository.StorylineRepository;
import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class StorylineMapRepository implements StorylineRepository {

    private final Map<Long, Stack<StorylineHistory>> map = new HashMap<>();
    private final Map<Long, Set<String>> historyUnitName = new HashMap<>();

    @Override
    public Uni<Void> save(@NotNull StorylineHistory history) {
        final Long personId = history.getPersonId();
        map.computeIfAbsent(personId, k -> new Stack<>()).push(history);
        historyUnitName.computeIfAbsent(personId, k -> new HashSet<>()).add(history.getUnitName());
        return Uni.createFrom().voidItem();
    }

    @Override
    public Uni<StorylineHistory> findByCountLast(long personId, int countUnitsToBack) {
        if (map.containsKey(personId)) {
            final Stack<StorylineHistory> stack = map.get(personId);
            if (stack.size() < countUnitsToBack) {
                countUnitsToBack = stack.size();
            }
            StorylineHistory storylineHistory = null;
            for (int i = 0; i < countUnitsToBack; i++) {
                storylineHistory = stack.pop();
                historyUnitName.get(personId).remove(storylineHistory.getUnitName());
            }
            return Uni.createFrom().item(storylineHistory);
        }
        return Uni.createFrom().nullItem();
    }

    @Override
    public Uni<StorylineHistory> findByCountLast(long personId, String unitName) {
        if (map.containsKey(personId)) {
            final Stack<StorylineHistory> stack = map.get(personId);
            StorylineHistory storylineHistory;
            while (!stack.isEmpty() && historyUnitName.get(personId).contains(unitName)) {
                storylineHistory = stack.pop();
                historyUnitName.get(personId).remove(storylineHistory.getUnitName());
                if (unitName.equals(storylineHistory.getUnitName())) {
                    return Uni.createFrom().item(storylineHistory);
                }
            }
        }
        return Uni.createFrom().nullItem();
    }

    @Override
    public Uni<Void> cleanHistoryByPersonId(@NotNull Long personId) {
        if (map.containsKey(personId)) {
            map.get(personId).clear();
        } else {
            map.put(personId, new Stack<>());
        }
        return Uni.createFrom().voidItem();
    }

}
