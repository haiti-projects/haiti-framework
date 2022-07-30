package dev.struchkov.godfather.core;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import dev.struchkov.haiti.utils.Inspector;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static dev.struchkov.haiti.utils.Checker.checkNull;

public class Storyline<M extends Message> {

    private final Set<MainUnit<M>> startingUnits = new HashSet<>();
    private final Set<MainUnit<M>> globalUnits = new HashSet<>();
    private final Map<String, MainUnit<M>> units = new HashMap<>();

    public Storyline(Set<MainUnit<M>> startingUnits, Map<String, MainUnit<M>> units) {
        this.startingUnits.addAll(startingUnits);
        this.units.putAll(units);
    }

    public void addGlobalUnits(Set<MainUnit<M>> globalUnits) {
        this.globalUnits.addAll(globalUnits);
    }

    public Set<MainUnit<M>> getGlobalUnits() {
        return globalUnits;
    }

    /**
     * Получить юнит по названию.
     *
     * @param unitName Название юнита.
     */
    public Optional<MainUnit<M>> getUnit(String unitName) {
        Inspector.isNotNull(unitName);
        return Optional.ofNullable(units.get(unitName));
    }

    public Set<MainUnit<M>> getStartingUnits() {
        return startingUnits;
    }

    //TODO [22.06.2022]: Временное решение ленивой связки юнитов, пока не будет реализован нормальный механизм.
    public void link(@NotNull String firstName, @NotNull String secondName) {
        Inspector.isNotNull(firstName, secondName);
        final MainUnit<M> firstUnit = units.get(firstName);
        final MainUnit<M> secondUnit = units.get(secondName);
        Inspector.isNotNull(firstUnit, secondUnit);
        if (checkNull(firstUnit.getNextUnits())) {
            firstUnit.setNextUnits(new HashSet<>());
        }
        firstUnit.getNextUnits().add(secondUnit);
    }

}
