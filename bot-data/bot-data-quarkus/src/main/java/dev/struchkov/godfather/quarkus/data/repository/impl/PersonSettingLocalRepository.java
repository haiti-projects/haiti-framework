package dev.struchkov.godfather.quarkus.data.repository.impl;

import dev.struchkov.godfather.quarkus.data.repository.PersonSettingRepository;
import io.smallrye.mutiny.Uni;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonSettingLocalRepository implements PersonSettingRepository {

    private final Map<Long, Boolean> map = new HashMap<>();

    @Override
    public Uni<Set<Long>> findAllByAllowedProcessing(Set<Long> personIds) {
        return Uni.createFrom().item(
                personIds.stream()
                        .filter(map::get)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public Uni<Void> disableMessageProcessing(Long personId) {
        map.put(personId, false);
        return Uni.createFrom().voidItem();
    }

    @Override
    public Uni<Void> enableMessageProcessing(Long personId) {
        map.put(personId, true);
        return Uni.createFrom().voidItem();
    }

    @Override
    public Uni<Boolean> findStateByPersonId(Long personId) {
        return Uni.createFrom().item(map.get(personId));
    }

}
