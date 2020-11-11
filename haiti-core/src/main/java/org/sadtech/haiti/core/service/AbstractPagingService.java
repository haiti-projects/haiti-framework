package org.sadtech.haiti.core.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.sadtech.haiti.context.page.Pagination;
import org.sadtech.haiti.context.page.Sheet;
import org.sadtech.haiti.context.repository.simple.PagingOperation;
import org.sadtech.haiti.context.service.simple.PagingService;

@RequiredArgsConstructor
public abstract class AbstractPagingService<T> implements PagingService<T> {

    private final PagingOperation<T> pagingOperation;

    @Override
    public Sheet<T> getAll(@NonNull Pagination pagination) {
        return pagingOperation.findAll(pagination);
    }

}
