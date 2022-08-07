package dev.struchkov.godfather.main.domain;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class UnitDefinition {

    private final Set<String> nextUnitNames = new HashSet<>();
    private final Set<String> dependentUnits = new HashSet<>();

    private String name;
    private Object objectConfig;
    private Method method;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObjectConfig() {
        return objectConfig;
    }

    public void setObjectConfig(Object objectConfig) {
        this.objectConfig = objectConfig;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Set<String> getNextUnitNames() {
        return new HashSet<>(nextUnitNames);
    }

    public void setNextUnitNames(Set<String> nextUnitNames) {
        this.nextUnitNames.addAll(nextUnitNames);
    }

    public void setNextUnitName(String nextUnitName) {
        this.nextUnitNames.add(nextUnitName);
    }

    public void removeNextUnit(String nextUnitName) {
        this.nextUnitNames.remove(nextUnitName);
    }

    public void removeDependentUnit(String dependentUnitName) {
        this.dependentUnits.remove(dependentUnitName);
    }

    public Set<String> getDependentUnits() {
        return new HashSet<>(dependentUnits);
    }

    public void setDependentUnits(Set<String> dependentUnitNames) {
        this.dependentUnits.addAll(dependentUnitNames);
    }

}
