package dev.struchkov.haiti.context.page.impl;

import dev.struchkov.haiti.context.page.Sheet;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SheetImpl<Entity> implements Sheet<Entity> {

    private final int number;
    private final int size;
    private final long totalElement;
    private final int totalPage;
    private final List<Entity> content;

    public SheetImpl(int number, int size, long totalElement, int totalPage, List<Entity> content) {
        this.number = number;
        this.size = size;
        this.totalElement = totalElement;
        this.totalPage = totalPage;
        this.content = content;
    }

    @Override
    public <U> Sheet<U> map(Function<? super Entity, ? extends U> function) {
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

    public Sheet<Entity> filter(Predicate<? super Entity> predicate) {
        final List<Entity> filterContent = content.stream().filter(predicate).collect(Collectors.toList());
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

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public long getTotalElement() {
        return totalElement;
    }

    @Override
    public int getTotalPage() {
        return totalPage;
    }

    @Override
    public List<Entity> getContent() {
        return content;
    }

}
