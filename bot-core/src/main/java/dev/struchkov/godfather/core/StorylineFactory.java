package dev.struchkov.godfather.core;

import dev.struchkov.godfather.context.domain.UnitDefinition;
import dev.struchkov.godfather.context.domain.annotation.Unit;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import dev.struchkov.godfather.context.exception.UnitConfigException;
import dev.struchkov.haiti.utils.Inspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static dev.struchkov.godfather.context.exception.UnitConfigException.unitConfigException;

public class StorylineFactory<M extends Message> {

    private static final Logger log = LoggerFactory.getLogger(StorylineFactory.class);

    private final List<Object> configurations = new ArrayList<>();

    private final Map<String, UnitDefinition> unitDefinitions = new HashMap<>();
    private final Map<String, MainUnit<M>> unitMap = new HashMap<>();

    private final Set<String> mainUnits = new HashSet<>();
    private final Set<String> globalUnits = new HashSet<>();

    public StorylineFactory(List<Object> unitConfigurations) {
        this.configurations.addAll(unitConfigurations);
    }

    public Map<String, UnitDefinition> getUnitDefinitions() {
        return unitDefinitions;
    }

    public Map<String, MainUnit<M>> getUnitMap() {
        return unitMap;
    }

    public Storyline<M> createStoryLine() {
        generateUnitDefinitions();
        try {
            createUnitMap();
            final Set<MainUnit<M>> mainUnit = getMainUnit();
            final Set<MainUnit<M>> globalUnit = getGlobalUnit();
            final Storyline<M> storyline = new Storyline<>(mainUnit, unitMap);
            storyline.addGlobalUnits(globalUnit);
            return storyline;
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
        throw new UnitConfigException("Ошибка построения StoryLine");
    }

    private Set<MainUnit<M>> getMainUnit() {
        Inspector.isNotEmpty(mainUnits, unitConfigException("Не задан ни один mainUnit. Установите хотя бы для одного Unit флаг mainUnit"));
        return mainUnits.stream()
                .map(unitMap::get)
                .collect(Collectors.toSet());
    }

    private Set<MainUnit<M>> getGlobalUnit() {
        return globalUnits.stream()
                .map(unitMap::get)
                .collect(Collectors.toSet());
    }

    private void createUnitMap() throws IllegalAccessException, InvocationTargetException {
        for (UnitDefinition unitDefinition : unitDefinitions.values()) {
            if (!unitMap.containsKey(unitDefinition.getName())) {
                final Set<String> nextUnitNames = unitDefinition.getNextUnitNames();
                if (nextUnitNames.isEmpty() || unitMap.keySet().containsAll(nextUnitNames)) {
                    createUnit(unitDefinition);
                }
            }
        }
    }

    private MainUnit<M> createUnit(UnitDefinition unitDefinition) throws IllegalAccessException, InvocationTargetException {
        final Object objectConfig = unitDefinition.getObjectConfig();
        final String currentUnitName = unitDefinition.getName();
        final Method method = unitDefinition.getMethod();
        final Object[] nextUnits = Arrays.stream(method.getParameters())
                .filter(parameter -> parameter.isAnnotationPresent(Unit.class))
                .map(parameter -> parameter.getAnnotation(Unit.class))
                .map(Unit::value)
                .map(unitMap::get)
                .toArray();
        MainUnit<M> newUnit = (MainUnit<M>) method.invoke(objectConfig, nextUnits);
        newUnit.setName(currentUnitName);

        unitMap.put(currentUnitName, newUnit);

        final Set<String> dependentUnitsName = unitDefinition.getDependentUnits();
        for (String dependentUnitName : dependentUnitsName) {
            final Set<String> dependentNextUnitNames = unitDefinitions.get(dependentUnitName).getNextUnitNames();
            if (unitMap.keySet().containsAll(dependentNextUnitNames)) {
                createUnit(unitDefinitions.get(dependentUnitName));
            }
        }

        return newUnit;
    }

    private void generateUnitDefinitions() {
        final Map<String, Set<String>> dependentUnits = new HashMap<>();

        for (Object config : configurations) {
            final Class<?> classUnitConfig = config.getClass();

            for (Method method : classUnitConfig.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Unit.class)) {
                    final Unit unitConfig = method.getAnnotation(Unit.class);

                    final String unitName = unitConfig.value();

                    final UnitDefinition unitDefinition = new UnitDefinition();
                    unitDefinition.setName(unitName);
                    unitDefinition.setMethod(method);
                    unitDefinition.setObjectConfig(config);

                    if (unitConfig.main()) {
                        mainUnits.add(unitName);
                    }
                    if (unitConfig.global()) {
                        globalUnits.add(unitName);
                    }

                    final Parameter[] nextUnits = method.getParameters();
                    if (nextUnits.length > 0) {
                        for (Parameter nextUnit : nextUnits) {
                            if (nextUnit.isAnnotationPresent(Unit.class)) {
                                final Unit nextUnitConfig = nextUnit.getAnnotation(Unit.class);
                                final String nextUnitName = nextUnitConfig.value();
                                unitDefinition.setNextUnitName(nextUnitName);
                                dependentUnits.computeIfAbsent(nextUnitName, k -> new HashSet<>());
                                dependentUnits.get(nextUnitName).add(unitName);
                            }
                        }
                    }

                    unitDefinitions.put(unitDefinition.getName(), unitDefinition);
                }
            }
        }

        for (Map.Entry<String, Set<String>> entry : dependentUnits.entrySet()) {
            final UnitDefinition unitDefinition = unitDefinitions.get(entry.getKey());
            if (unitDefinition != null) {
                unitDefinition.setDependentUnits(entry.getValue());
            } else {
                throw new UnitConfigException("Ошибка связи юнитов. Проблема с описанием {0} юнита. Возможно вы не указали класс конфигурации для этого юнита.", entry.getKey());
            }
        }
    }

}
