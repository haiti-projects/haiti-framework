package dev.struchkov.haiti.context.page.impl;

import dev.struchkov.haiti.context.enums.TypeSort;
import dev.struchkov.haiti.context.page.Sort;

import static dev.struchkov.haiti.utils.Assert.isNotNull;

public class SortImpl implements Sort {

    private final TypeSort type;
    private final String field;

    private SortImpl(TypeSort type, String field) {
        this.type = type;
        this.field = field;
    }

    public static Sort of(TypeSort type, String field) {
        isNotNull(type);
        return new SortImpl(type, field);
    }

    @Override
    public TypeSort getType() {
        return type;
    }

    @Override
    public String getField() {
        return field;
    }

}
