package dev.struchkov.godfather.simple.core.service;

import dev.struchkov.godfather.main.domain.ContextKey;
import dev.struchkov.godfather.simple.data.StorylineContext;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static dev.struchkov.haiti.context.exception.NotFoundException.notFoundException;
import static dev.struchkov.haiti.utils.Inspector.isNotNull;

public class StorylineContextMapImpl implements StorylineContext {

    private final Map<Long, Map<String, Object>> map = new HashMap<>();

    public void save(@NotNull Long personId, @NotNull ContextKey<?> key, Object objectForSave) {
        isNotNull(personId, key);
        map.computeIfAbsent(personId, k -> new HashMap<>()).put(key.getValue(), objectForSave);
    }

    public <T> Optional<T> getByKey(@NotNull Long personId, @NotNull ContextKey<T> key) {
        isNotNull(personId, key);
        if (map.containsKey(personId)) {
            final Map<String, Object> storage = map.get(personId);
            final T object = (T) storage.get(key.getValue());
            if (object != null && object.getClass().equals(key.getType())) {
                return Optional.of(object);
            }
        }
        return Optional.empty();
    }

    @Override
    public <T> T getByKeyOrThrow(@NotNull Long personId, @NotNull ContextKey<T> key) {
        return getByKey(personId, key).orElseThrow(notFoundException("Не найдено значение ключа {0}, для пользователя {1}", key.getValue(), personId));
    }

    public Map<String, Object> getAllSaveElement(@NotNull Long personId) {
        isNotNull(personId);
        return map.get(personId);
    }

    public void removeAll(@NotNull Long personId) {
        isNotNull(personId);
        map.remove(personId);
    }

    public void removeByKey(@NotNull Long personId, @NotNull ContextKey<?> key) {
        isNotNull(personId, key);
        map.computeIfAbsent(personId, k -> new HashMap<>()).remove(key.getValue());
    }

}
