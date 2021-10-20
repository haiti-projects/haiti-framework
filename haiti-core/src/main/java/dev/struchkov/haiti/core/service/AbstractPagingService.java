package dev.struchkov.haiti.core.service;

import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.context.repository.simple.PagingOperation;
import dev.struchkov.haiti.context.service.simple.PagingService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractPagingService<T> implements PagingService<T> {

    private final PagingOperation<T> pagingOperation;

    @Override
    public Sheet<T> getAll(@NonNull Pagination pagination) {
        return pagingOperation.findAll(pagination);
    }

}
