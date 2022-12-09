package dev.struchkov.godfather.simple.data.repository;

import java.util.Optional;
import java.util.Set;

public interface PersonSettingRepository {

    Set<String> findAllByAllowedProcessing(Set<String> personIds);

    void disableMessageProcessing(String personId);

    void enableMessageProcessing(String personId);

    Optional<Boolean> findStateByPersonId(String personId);

}
