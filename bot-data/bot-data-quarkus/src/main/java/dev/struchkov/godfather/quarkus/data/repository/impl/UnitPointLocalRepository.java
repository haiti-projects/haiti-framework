package dev.struchkov.godfather.quarkus.data.repository.impl;

import dev.struchkov.godfather.main.domain.UnitPointer;
import dev.struchkov.godfather.quarkus.data.repository.UnitPointerRepository;
import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class UnitPointLocalRepository implements UnitPointerRepository {

    public final Map<Long, String> map = new HashMap<>();

    @Override
    public Uni<UnitPointer> save(@NotNull UnitPointer unitPointer) {
        map.put(unitPointer.getPersonId(), unitPointer.getUnitName());
        return Uni.createFrom().item(unitPointer);
    }

    @Override
    public Uni<String> findUnitNameByPersonId(@NotNull Long personId) {
        return Uni.createFrom().item(map.get(personId));
    }

    @Override
    public Uni<Void> removeByPersonId(@NotNull Long personId) {
        map.remove(personId);
        return Uni.createFrom().voidItem();
    }

}
