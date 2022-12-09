package dev.struchkov.godfather.quarkus.data;

import dev.struchkov.godfather.main.domain.ContextKey;
import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface StorylineContext {

    Uni<Void> save(@NotNull String personId, @NotNull ContextKey<?> key, Object objectForSave);

    <T> Uni<T> getByKey(@NotNull String personId, @NotNull ContextKey<T> key);

    <T> Uni<T> getByKeyOrThrow(@NotNull String personId, @NotNull ContextKey<T> key);

    Uni<Map<String, Object>> getAllSaveElement(@NotNull String personId);

    Uni<Void> removeAll(@NotNull String personId);

    Uni<Void> removeByKey(@NotNull String personId, @NotNull ContextKey<?> key);

}
