package dev.struchkov.godfather.quarkus.context.service;

import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface PersonSettingService {

    Uni<Set<Long>> getAllPersonIdDisableMessages(@NotNull Set<Long> personIds);

    Uni<Boolean> getStateProcessingByPersonId(@NotNull Long personId);

    Uni<Void> disableMessageProcessing(@NotNull Long personId);

    Uni<Void> enableMessageProcessing(@NotNull Long personId);

}
