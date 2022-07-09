package dev.struchkov.godfather.context.domain.unit;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.godfather.context.domain.UnitDefinition;
import dev.struchkov.godfather.context.domain.keyboard.KeyBoard;
import dev.struchkov.godfather.context.service.Accessibility;

import java.util.Set;
import java.util.regex.Pattern;

public class LazyUnit extends MainUnit {

    private final UnitDefinition unitDefinition;

    private LazyUnit(
            String name,
            Set<KeyWord> keyWords,
            String phrase,
            Pattern pattern,
            Integer matchThreshold,
            Integer priority,
            Set<MainUnit> nextUnits,
            UnitActiveType activeType,
            String type,
            UnitDefinition unitDefinition,
            Accessibility accessibility
    ) {
        super(name, keyWords, phrase, pattern, matchThreshold, priority, nextUnits, activeType, accessibility, type);
        this.unitDefinition = unitDefinition;
    }

    public static LazyUnit create(String name, UnitDefinition unitDefinition) {
        return new LazyUnit(name, null, null, null, null, null, null, null, null, unitDefinition, null);
    }

}
