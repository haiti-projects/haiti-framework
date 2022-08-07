package dev.struchkov.godfather.quarkus.core.service;

import dev.struchkov.godfather.main.domain.ContextKey;
import dev.struchkov.godfather.quarkus.data.StorylineContext;
import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static dev.struchkov.haiti.context.exception.NotFoundException.notFoundException;
import static dev.struchkov.haiti.utils.Inspector.isNotNull;

public class StorylineContextMapImpl implements StorylineContext {

    private final Map<Long, Map<String, Object>> map = new HashMap<>();

    public Uni<Void> save(@NotNull Long personId, @NotNull ContextKey<?> key, Object objectForSave) {
        isNotNull(personId, key);
        map.computeIfAbsent(personId, k -> new HashMap<>()).put(key.getValue(), objectForSave);
        return Uni.createFrom().voidItem();
    }

    public <T> Uni<T> getByKey(@NotNull Long personId, @NotNull ContextKey<T> key) {
        isNotNull(personId, key);
        if (map.containsKey(personId)) {
            final Map<String, Object> storage = map.get(personId);
            final T object = (T) storage.get(key.getValue());
            if (object != null && object.getClass().equals(key.getType())) {
                return Uni.createFrom().item(object);
            }
        }
        return Uni.createFrom().nullItem();
    }

    @Override
    public <T> Uni<T> getByKeyOrThrow(@NotNull Long personId, @NotNull ContextKey<T> key) {
        return getByKey(personId, key)
                .onItem().ifNull().failWith(notFoundException("Не найдено значение ключа {0}, для пользователя {1}", key.getValue(), personId));
    }

    public Uni<Map<String, Object>> getAllSaveElement(@NotNull Long personId) {
        isNotNull(personId);
        return Uni.createFrom().item(map.get(personId));
    }

    public Uni<Void> removeAll(@NotNull Long personId) {
        isNotNull(personId);
        map.remove(personId);
        return Uni.createFrom().voidItem();
    }

    public Uni<Void> removeByKey(@NotNull Long personId, @NotNull ContextKey<?> key) {
        isNotNull(personId, key);
        map.computeIfAbsent(personId, k -> new HashMap<>()).remove(key.getValue());
        return Uni.createFrom().voidItem();
    }

}
