package dev.struchkov.godfather.context.repository.impl.local;

import dev.struchkov.godfather.context.domain.StorylineHistory;
import dev.struchkov.godfather.context.repository.StorylineRepository;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;

public class StorylineMapRepository implements StorylineRepository {

    private final Map<Long, Stack<StorylineHistory>> map = new HashMap<>();

    @Override
    public void save(@NotNull StorylineHistory history) {
        final Long personId = history.getPersonId();
        map.computeIfAbsent(personId, k -> new Stack<>()).push(history);
    }

    @Override
    public Optional<StorylineHistory> findByCountLast(long personId, int countUnitsToBack) {
        if (map.containsKey(personId)) {
            final Stack<StorylineHistory> stack = map.get(personId);
            if (stack.size() < countUnitsToBack) {
                countUnitsToBack = stack.size();
            }
            StorylineHistory storylineHistory = null;
            for (int i = 0; i < countUnitsToBack; i++) {
                storylineHistory = stack.pop();
            }
            return Optional.ofNullable(storylineHistory);
        }
        return Optional.empty();
    }

    @Override
    public Optional<StorylineHistory> findByCountLast(long personId, String unitName) {
        if (map.containsKey(personId)) {
            final Stack<StorylineHistory> stack = map.get(personId);
            StorylineHistory storylineHistory;
            while (!stack.isEmpty()) {
                storylineHistory = stack.pop();
                if (unitName.equals(storylineHistory.getUnitName())) {
                    return Optional.of(storylineHistory);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void cleanHistoryByPersonId(@NotNull Long personId) {
        if (map.containsKey(personId)) {
            map.get(personId).clear();
        } else {
            map.put(personId, new Stack<>());
        }
    }

}
