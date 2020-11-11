package org.sadtech.haiti.database.repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.sadtech.haiti.context.page.Pagination;
import org.sadtech.haiti.context.page.Sheet;
import org.sadtech.haiti.filter.Filter;
import org.sadtech.haiti.filter.FilterOperation;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractFilterOperation<T, K> implements FilterOperation<T> {

    private final JpaRepositoryImplementation<T, K> jpaRepository;

    @Override
    public Sheet<T> findAll(@NonNull Filter filter, @NonNull Pagination pagination) {
        return OperationJpa.findAll(jpaRepository, filter.build(), pagination);
    }

    @Override
    public Optional<T> findFirst(@NonNull Filter filter) {
        return OperationJpa.findFirst(jpaRepository, filter.build());
    }

    @Override
    public boolean exists(@NonNull Filter filter) {
        return OperationJpa.exists(jpaRepository, filter.build());
    }

    @Override
    public long count(@NonNull Filter filter) {
        return OperationJpa.count(jpaRepository, filter.build());
    }

}
