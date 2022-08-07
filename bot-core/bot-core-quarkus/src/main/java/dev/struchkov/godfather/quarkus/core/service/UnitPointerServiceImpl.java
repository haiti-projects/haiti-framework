package dev.struchkov.godfather.quarkus.core.service;

import dev.struchkov.godfather.main.domain.UnitPointer;
import dev.struchkov.godfather.quarkus.context.service.UnitPointerService;
import dev.struchkov.godfather.quarkus.data.repository.UnitPointerRepository;
import io.smallrye.mutiny.Uni;
import org.jetbrains.annotations.NotNull;

public class UnitPointerServiceImpl implements UnitPointerService {

    private final UnitPointerRepository unitPointerRepository;

    public UnitPointerServiceImpl(UnitPointerRepository unitPointerRepository) {
        this.unitPointerRepository = unitPointerRepository;
    }

    @Override
    public Uni<UnitPointer> save(@NotNull UnitPointer unitPointer) {
        return unitPointerRepository.save(unitPointer);
    }

    @Override
    public Uni<String> getUnitNameByPersonId(@NotNull Long personId) {
        return unitPointerRepository.findUnitNameByPersonId(personId);
    }

    @Override
    public Uni<Void> removeByPersonId(@NotNull Long personId) {
        return unitPointerRepository.removeByPersonId(personId);
    }

}
