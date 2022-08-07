package dev.struchkov.godfather.quarkus.core.unit;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.autoresponder.entity.Unit;
import dev.struchkov.godfather.main.core.unit.UnitActiveType;
import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.quarkus.context.service.Accessibility;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Главный обработчик {@link Unit}, от него наследуются все остальные Unit-ы.
 *
 * @author upagge [08/07/2019]
 */
public abstract class MainUnit<M extends Message> extends Unit<MainUnit<M>, M> {

    /**
     * Уникальное имя юнита
     */
    private String name;

    /**
     * Описание юнита, что он делает. Никак не влияет на работу юнита и не участвует в ней. Возможно отображение этого текста в логах.
     */
    private final String description;

    /**
     * Тип Unit-а.
     */
    protected final String type;

    /**
     * Режим срабатывания Unit-а.
     */
    protected UnitActiveType activeType;

    /**
     * Проверка доступа пользователя к юниту.
     */
    private final Accessibility accessibility;

    private final boolean notSaveHistory;

    protected MainUnit(
            String name,
            String description,
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
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainUnit<?> unit = (MainUnit<?>) o;
        return name.equals(unit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
