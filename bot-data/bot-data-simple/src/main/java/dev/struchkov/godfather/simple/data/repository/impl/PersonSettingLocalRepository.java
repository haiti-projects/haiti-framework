package dev.struchkov.godfather.simple.data.repository.impl;

import dev.struchkov.godfather.simple.data.repository.PersonSettingRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonSettingLocalRepository implements PersonSettingRepository {

    private final Map<String, Boolean> map = new HashMap<>();

    @Override
    public Set<String> findAllByAllowedProcessing(Set<String> personIds) {
        return personIds.stream()
                .filter(map::get)
                .collect(Collectors.toSet());
    }

    @Override
    public void disableMessageProcessing(String personId) {
        map.put(personId, false);
    }

    @Override
    public void enableMessageProcessing(String personId) {
        map.put(personId, true);
    }

    @Override
    public Optional<Boolean> findStateByPersonId(String personId) {
        return Optional.ofNullable(map.get(personId));
    }

}
