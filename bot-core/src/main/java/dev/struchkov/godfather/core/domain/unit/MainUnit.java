package dev.struchkov.godfather.core.domain.unit;

import dev.struchkov.autoresponder.entity.Unit;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Главный обработчик {@link Unit}, от него наследуются все остальные Unit-ы.
 *
 * @author upagge [08/07/2019]
 */
public abstract class MainUnit extends Unit<MainUnit> {

    /**
     * Тип Unit-а.
     */
    protected final String type;

    /**
     * Режим срабатывания Unit-а.
     */
    protected UnitActiveType activeType;

    private final String uuid = UUID.randomUUID().toString();

    protected MainUnit(
            Set<String> keyWords,
            String phrase,
            Pattern pattern,
            Integer matchThreshold,
            Integer priority,
            Set<MainUnit> nextUnits,
            UnitActiveType activeType,
            String type
    ) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits);
        this.activeType = Optional.ofNullable(activeType).orElse(UnitActiveType.DEFAULT);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setActiveType(UnitActiveType activeType) {
        this.activeType = activeType;
    }

    public UnitActiveType getActiveType() {
        return activeType;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MainUnit mainUnit = (MainUnit) o;
        return Objects.equals(type, mainUnit.type) && activeType == mainUnit.activeType && Objects.equals(uuid, mainUnit.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, activeType, uuid);
    }

}
