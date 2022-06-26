package dev.struchkov.godfather.context.repository.impl.local;

import dev.struchkov.godfather.context.repository.PersonSettingRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonSettingLocalRepository implements PersonSettingRepository {

    private final Map<Long, Boolean> map = new HashMap<>();

    @Override
    public Set<Long> findAllByAllowedProcessing(Set<Long> personIds) {
        return personIds.stream()
                .filter(map::get)
                .collect(Collectors.toSet());
    }

    @Override
    public void disableMessageProcessing(Long personId) {
        map.put(personId, false);
    }

    @Override
    public void enableMessageProcessing(Long personId) {
        map.put(personId, true);
    }

    @Override
    public Optional<Boolean> findStateByPersonId(Long personId) {
        return Optional.ofNullable(map.get(personId));
    }

}
