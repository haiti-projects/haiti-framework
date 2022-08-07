package dev.struchkov.godfather.quarkus.data.repository;

import io.smallrye.mutiny.Uni;

import java.util.Set;

public interface PersonSettingRepository {

    Uni<Set<Long>> findAllByAllowedProcessing(Set<Long> personIds);

    Uni<Void> disableMessageProcessing(Long personId);

    Uni<Void> enableMessageProcessing(Long personId);

    Uni<Boolean> findStateByPersonId(Long personId);

}
