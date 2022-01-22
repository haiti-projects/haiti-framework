package dev.struchkov.haiti.core.service;

import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.context.repository.simple.PagingOperation;
import dev.struchkov.haiti.context.service.simple.PagingService;
import dev.struchkov.haiti.utils.Assert;

public abstract class AbstractPagingService<T> implements PagingService<T> {

    private final PagingOperation<T> pagingOperation;

    protected AbstractPagingService(PagingOperation<T> pagingOperation) {
        this.pagingOperation = pagingOperation;
    }

    @Override
    public Sheet<T> getAll(Pagination pagination) {
        Assert.isNotNull(pagination);
        return pagingOperation.findAll(pagination);
    }

}
