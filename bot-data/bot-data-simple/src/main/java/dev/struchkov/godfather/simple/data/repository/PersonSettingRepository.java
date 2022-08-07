package dev.struchkov.godfather.simple.data.repository;

import java.util.Optional;
import java.util.Set;

public interface PersonSettingRepository {

    Set<Long> findAllByAllowedProcessing(Set<Long> personIds);

    void disableMessageProcessing(Long personId);

    void enableMessageProcessing(Long personId);

    Optional<Boolean> findStateByPersonId(Long personId);

}
