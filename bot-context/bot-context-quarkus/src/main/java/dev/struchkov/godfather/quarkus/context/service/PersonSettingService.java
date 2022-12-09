package dev.struchkov.godfather.quarkus.context.service;

import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface PersonSettingService {

    Uni<Set<String>> getAllPersonIdDisableMessages(@NotNull Set<String> personIds);

    Uni<Boolean> getStateProcessingByPersonId(@NotNull String personId);

    Uni<Void> disableMessageProcessing(@NotNull String personId);

    Uni<Void> enableMessageProcessing(@NotNull String personId);

}
