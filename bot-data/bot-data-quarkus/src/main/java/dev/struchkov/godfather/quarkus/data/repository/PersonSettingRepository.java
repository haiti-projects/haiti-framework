package dev.struchkov.godfather.quarkus.data.repository;

import io.smallrye.mutiny.Uni;

import java.util.Set;

public interface PersonSettingRepository {

    Uni<Set<String>> findAllByAllowedProcessing(Set<String> personIds);

    Uni<Void> disableMessageProcessing(String personId);

    Uni<Void> enableMessageProcessing(String personId);

    Uni<Boolean> findStateByPersonId(String personId);

}
