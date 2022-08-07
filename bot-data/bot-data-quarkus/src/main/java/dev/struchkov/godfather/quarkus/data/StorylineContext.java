package dev.struchkov.godfather.quarkus.data;

import dev.struchkov.godfather.main.domain.ContextKey;
import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface StorylineContext {

    Uni<Void> save(@NotNull Long telegramId, @NotNull ContextKey<?> key, Object objectForSave);

    <T> Uni<T> getByKey(@NotNull Long telegramId, @NotNull ContextKey<T> key);

    <T> Uni<T> getByKeyOrThrow(@NotNull Long telegramId, @NotNull ContextKey<T> key);

    Uni<Map<String, Object>> getAllSaveElement(@NotNull Long telegramId);

    Uni<Void> removeAll(@NotNull Long telegramId);

    Uni<Void> removeByKey(@NotNull Long telegramId, @NotNull ContextKey<?> key);

}
