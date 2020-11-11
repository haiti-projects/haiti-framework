package org.sadtech.haiti.core.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.sadtech.haiti.context.page.Pagination;
import org.sadtech.haiti.context.page.Sheet;
import org.sadtech.haiti.filter.Filter;
import org.sadtech.haiti.filter.FilterOperation;
import org.sadtech.haiti.filter.FilterService;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractFilterService<T, F> implements FilterService<T, F> {

    private final FilterOperation<T> filterOperation;

    @Override
    public Sheet<T> getAll(@NonNull F filter, Pagination pagination) {
        return filterOperation.findAll(createFilter(filter), pagination);
    }

    @Override
    public Optional<T> getFirst(@NonNull F filter) {
        return filterOperation.findFirst(createFilter(filter));
    }

    @Override
    public boolean exists(@NonNull F filter) {
        return filterOperation.exists(createFilter(filter));
    }

    @Override
    public long count(@NonNull F filter) {
        return filterOperation.count(createFilter(filter));
    }

    protected abstract Filter createFilter(@NonNull F filter);

}
