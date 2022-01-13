package dev.struchkov.haiti.context.page.impl;

import dev.struchkov.haiti.context.enums.TypeSort;
import dev.struchkov.haiti.context.page.Sort;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SortImpl implements Sort {

    private final TypeSort type;
    private final String field;

    public static Sort of(@NonNull TypeSort type, String field) {
        return new SortImpl(type, field);
    }

}
