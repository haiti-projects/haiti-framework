package dev.struchkov.godfather.context.repository.impl.local;

import dev.struchkov.godfather.context.domain.UnitPointer;
import dev.struchkov.godfather.context.repository.UnitPointerRepository;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UnitPointLocalRepository implements UnitPointerRepository {

    public static final Map<Long, String> map = new HashMap<>();

    @Override
    public UnitPointer save(@NotNull UnitPointer unitPointer) {
        map.put(unitPointer.getPersonId(), unitPointer.getUnitName());
        return unitPointer;
    }

    @Override
    public Optional<String> findUnitNameByPersonId(@NotNull Long personId) {
        return Optional.ofNullable(map.get(personId));
    }

    @Override
    public void removeByPersonId(@NotNull Long personId) {
        map.remove(personId);
    }

}
