package dev.struchkov.godfather.core.service;

import dev.struchkov.godfather.context.domain.UnitPointer;
import dev.struchkov.godfather.context.repository.UnitPointerRepository;
import dev.struchkov.godfather.context.service.UnitPointerService;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class UnitPointerServiceImpl implements UnitPointerService {

    private final UnitPointerRepository unitPointerRepository;

    public UnitPointerServiceImpl(UnitPointerRepository unitPointerRepository) {
        this.unitPointerRepository = unitPointerRepository;
    }

    @Override
    public UnitPointer save(@NotNull UnitPointer unitPointer) {
        return unitPointerRepository.save(unitPointer);
    }

    @Override
    public Optional<String> getUnitNameByPersonId(@NotNull Long personId) {
        return unitPointerRepository.findUnitNameByPersonId(personId);
    }

    @Override
    public void removeByPersonId(@NotNull Long personId) {
        unitPointerRepository.removeByPersonId(personId);
    }

}
