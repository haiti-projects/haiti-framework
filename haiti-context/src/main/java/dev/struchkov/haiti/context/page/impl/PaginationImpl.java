package dev.struchkov.haiti.context.page.impl;

import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sort;

import java.util.Set;

public class PaginationImpl implements Pagination {

    private final Integer page;
    private final Integer size;
    private Set<? extends Sort> sorts;

    private PaginationImpl(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public PaginationImpl(int page, int size, Set<? extends Sort> sorts) {
        this.page = page;
        this.size = size;
        this.sorts = sorts;
    }

    public static Pagination of(int page, int size) {
        return new PaginationImpl(page, size);
    }

    public static Pagination of(int page, int size, Set<? extends Sort> sorts) {
        return new PaginationImpl(page, size, sorts);
    }

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public Set<? extends Sort> getSorts() {
        return sorts;
    }
}
