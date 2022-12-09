package dev.struchkov.godfather.simple.context.service;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;

public interface PersonSettingService {

    Set<String> getAllPersonIdDisableMessages(@NotNull Set<String> personIds);

    Optional<Boolean> getStateProcessingByPersonId(@NotNull String personId);

    void disableMessageProcessing(@NotNull String personId);

    void enableMessageProcessing(@NotNull String personId);

}
