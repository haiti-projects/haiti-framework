package dev.struchkov.godfather.simple.core.service;

import dev.struchkov.godfather.main.domain.UnitPointer;
import dev.struchkov.godfather.simple.context.service.UnitPointerService;
import dev.struchkov.godfather.simple.data.repository.UnitPointerRepository;
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
