package dev.struchkov.godfather.core.domain.unit;

import dev.struchkov.autoresponder.entity.Unit;
import dev.struchkov.godfather.core.service.Accessibility;

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
     * Уникальный идентификатор юнита
     */
    private final String uuid = UUID.randomUUID().toString();

    /**
     * Режим срабатывания Unit-а.
     */
    protected UnitActiveType activeType;

    /**
     * Уникальное имя юнита
     */
    private String name;

    /**
     * Проверка доступа пользователя к юниту.
     */
    private Accessibility accessibility;

    protected MainUnit(
            String name,
            Set<String> keyWords,
            String phrase,
            Pattern pattern,
            Integer matchThreshold,
            Integer priority,
            Set<MainUnit> nextUnits,
            UnitActiveType activeType,
            Accessibility accessibility,
            String type
    ) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits);
        this.name = name;
        this.activeType = Optional.ofNullable(activeType).orElse(UnitActiveType.DEFAULT);
        this.accessibility = accessibility;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public UnitActiveType getActiveType() {
        return activeType;
    }

    public void setActiveType(UnitActiveType activeType) {
        this.activeType = activeType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Optional<Accessibility> getAccessibility() {
        return Optional.ofNullable(accessibility);
    }

    //TODO [27.05.2022]: Возможно стоит добавить имя юнита и убрать остальное
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
