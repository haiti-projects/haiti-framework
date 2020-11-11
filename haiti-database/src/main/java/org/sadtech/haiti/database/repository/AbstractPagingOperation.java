package org.sadtech.haiti.database.repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.sadtech.haiti.context.page.Pagination;
import org.sadtech.haiti.context.page.Sheet;
import org.sadtech.haiti.context.repository.simple.PagingOperation;
import org.springframework.data.jpa.repository.JpaRepository;

@RequiredArgsConstructor
public abstract class AbstractPagingOperation<T, K> implements PagingOperation<T> {

    private final JpaRepository<T, K> jpaRepository;

    @Override
    public Sheet<T> findAll(@NonNull Pagination pagination) {
        return OperationJpa.findAll(jpaRepository, pagination);
    }

}
