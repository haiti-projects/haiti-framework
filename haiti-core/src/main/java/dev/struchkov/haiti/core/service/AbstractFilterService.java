package dev.struchkov.haiti.core.service;

import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.context.service.simple.FilterService;
import dev.struchkov.haiti.filter.Filter;
import dev.struchkov.haiti.filter.FilterOperation;
import dev.struchkov.haiti.utils.Assert;

import java.util.Optional;

public abstract class AbstractFilterService<Entity, F> implements FilterService<Entity, F> {

    protected final FilterOperation<Entity> filterOperation;

    protected AbstractFilterService(FilterOperation<Entity> filterOperation) {
        this.filterOperation = filterOperation;
    }

    @Override
    public Sheet<Entity> getAll(F filter, Pagination pagination) {
        Assert.isNotNull(filter);
        return filterOperation.findAll(createFilter(filter), pagination);
    }

    @Override
    public Optional<Entity> getFirst(F filter) {
        Assert.isNotNull(filter);
        return filterOperation.findFirst(createFilter(filter));
    }

    @Override
    public boolean exists(F filter) {
        Assert.isNotNull(filter);
        return filterOperation.exists(createFilter(filter));
    }

    @Override
    public long count(F filter) {
        Assert.isNotNull(filter);
        return filterOperation.count(createFilter(filter));
    }

    protected abstract Filter createFilter(F filter);

}
