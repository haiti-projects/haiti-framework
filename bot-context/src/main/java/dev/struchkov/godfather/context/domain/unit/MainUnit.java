package dev.struchkov.godfather.context.domain.unit;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.autoresponder.entity.Unit;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.service.Accessibility;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Главный обработчик {@link Unit}, от него наследуются все остальные Unit-ы.
 *
 * @author upagge [08/07/2019]
 */
public abstract class MainUnit<M extends Message> extends Unit<MainUnit<M>, M> {

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
    private final Accessibility accessibility;

    private final boolean notSaveHistory;

    protected MainUnit(
            String name,
            Set<KeyWord> keyWords,
            Set<String> phrases,
            Predicate<M> triggerCheck,
            Set<Pattern> patterns,
            Integer matchThreshold,
            Integer priority,
            Set<MainUnit<M>> nextUnits,
            UnitActiveType activeType,
            boolean notSaveHistory,
            Accessibility accessibility,
            String type
    ) {
        super(keyWords, phrases, triggerCheck, patterns, matchThreshold, priority, nextUnits);
        this.name = name;
        this.activeType = Optional.ofNullable(activeType).orElse(UnitActiveType.DEFAULT);
        this.accessibility = accessibility;
        this.type = type;
        this.notSaveHistory = notSaveHistory;
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

    public boolean isNotSaveHistory() {
        return notSaveHistory;
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
