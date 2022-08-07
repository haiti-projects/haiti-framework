package dev.struchkov.godfather.simple.data;

import dev.struchkov.godfather.main.domain.ContextKey;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

public interface StorylineContext {

    void save(@NotNull Long telegramId, @NotNull ContextKey<?> key, Object objectForSave);

    <T> Optional<T> getByKey(@NotNull Long telegramId, @NotNull ContextKey<T> key);

    <T> T getByKeyOrThrow(@NotNull Long telegramId, @NotNull ContextKey<T> key);

    Map<String, Object> getAllSaveElement(@NotNull Long telegramId);

    void removeAll(@NotNull Long telegramId);

    void removeByKey(@NotNull Long telegramId, @NotNull ContextKey<?> key);

}
