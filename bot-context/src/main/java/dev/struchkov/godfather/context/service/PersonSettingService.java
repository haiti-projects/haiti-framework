package dev.struchkov.godfather.context.service;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;

public interface PersonSettingService {

    Set<Long> getAllPersonIdDisableMessages(@NotNull Set<Long> personIds);

    Optional<Boolean> getStateProcessingByPersonId(@NotNull Long personId);

    void disableMessageProcessing(@NotNull Long personId);

    void enableMessageProcessing(@NotNull Long personId);

}
