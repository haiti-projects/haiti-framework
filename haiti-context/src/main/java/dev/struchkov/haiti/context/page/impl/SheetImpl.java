package dev.struchkov.haiti.context.page.impl;

import dev.struchkov.haiti.context.page.Sheet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class SheetImpl<T> implements Sheet<T> {

    private final int number;
    private final int size;
    private final long totalElement;
    private final int totalPage;
    private final List<T> content;

    @Override
    public <U> Sheet<U> map(Function<? super T, ? extends U> function) {
        return new SheetImpl<>(
                this.number,
                this.size,
                this.totalElement,
                this.totalPage,
                this.content.stream().map(function).collect(Collectors.toList())
        );
    }

    public static <T> Sheet<T> empty() {
        return new SheetImpl<>(
                0,
                0,
                0L,
                0,
                Collections.emptyList()
        );
    }

    public Sheet<T> filter(Predicate<? super T> predicate) {
        final List<T> filterContent = content.stream().filter(predicate).collect(Collectors.toList());
        return new SheetImpl<>(
                this.number,
                this.size,
                this.totalElement,
                this.totalPage,
                filterContent
        );
    }

    public boolean hasContent() {
        return content != null && !content.isEmpty();
    }

}
