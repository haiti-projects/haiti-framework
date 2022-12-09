package dev.struchkov.godfather.simple.data;

import dev.struchkov.godfather.main.domain.ContextKey;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

public interface StorylineContext {

    void save(@NotNull String personId, @NotNull ContextKey<?> key, Object objectForSave);

    <T> Optional<T> getByKey(@NotNull String personId, @NotNull ContextKey<T> key);

    <T> T getByKeyOrThrow(@NotNull String personId, @NotNull ContextKey<T> key);

    Map<String, Object> getAllSaveElement(@NotNull String personId);

    void removeAll(@NotNull String personId);

    void removeByKey(@NotNull String personId, @NotNull ContextKey<?> key);

}
