package dev.struchkov.godfather.main.domain;

import java.util.Objects;

public class UnitPointer {

    private String personId;
    private String unitName;

    public UnitPointer(String personId, String unitName) {
        this.personId = personId;
        this.unitName = unitName;
    }

    public String getPersonId() {
        return personId;
    }

    public String getUnitName() {
        return unitName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitPointer that = (UnitPointer) o;
        return Objects.equals(personId, that.personId) && Objects.equals(unitName, that.unitName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, unitName);
    }

}
