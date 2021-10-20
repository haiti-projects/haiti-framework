package dev.struchkov.haiti.core.service;

import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.context.service.simple.FilterService;
import dev.struchkov.haiti.filter.Filter;
import dev.struchkov.haiti.filter.FilterOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractFilterService<T, F> implements FilterService<T, F> {

    protected final FilterOperation<T> filterOperation;

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
