package dev.struchkov.godfather.quarkus.data.repository.impl;

import dev.struchkov.godfather.quarkus.data.repository.PersonSettingRepository;
import io.smallrye.mutiny.Uni;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonSettingLocalRepository implements PersonSettingRepository {

    private final Map<String, Boolean> map = new HashMap<>();

    @Override
    public Uni<Set<String>> findAllByAllowedProcessing(Set<String> personIds) {
        return Uni.createFrom().item(
                personIds.stream()
                        .filter(map::get)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public Uni<Void> disableMessageProcessing(String personId) {
        map.put(personId, false);
        return Uni.createFrom().voidItem();
    }

    @Override
    public Uni<Void> enableMessageProcessing(String personId) {
        map.put(personId, true);
        return Uni.createFrom().voidItem();
    }

    @Override
    public Uni<Boolean> findStateByPersonId(String personId) {
        return Uni.createFrom().item(map.get(personId));
    }

}
