package org.sadtech.haiti.database.repository.manager;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.sadtech.haiti.context.page.Pagination;
import org.sadtech.haiti.context.page.Sheet;
import org.sadtech.haiti.database.repository.OperationJpa;
import org.sadtech.haiti.filter.Filter;
import org.sadtech.haiti.filter.FilterOperation;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

@Slf4j
public abstract class FilterManagerRepository<T, K> extends AbstractSimpleManagerRepository<T, K> implements FilterOperation<T> {

    private final JpaRepositoryImplementation<T, K> jpaRepositoryImplementation;

    public FilterManagerRepository(JpaRepositoryImplementation<T, K> jpaRepository) {
        super(jpaRepository);
        this.jpaRepositoryImplementation = jpaRepository;
    }

    @Override
    public Sheet<T> findAll(@NonNull Filter filter, @NonNull Pagination pagination) {
        return OperationJpa.findAll(jpaRepositoryImplementation, filter.build(), pagination);
    }

    @Override
    public Optional<T> findFirst(@NonNull Filter filter) {
        try {
            return OperationJpa.findFirst(jpaRepositoryImplementation, filter.build());
        } catch (InvalidDataAccessResourceUsageException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean exists(@NonNull Filter filter) {
        try {
            return OperationJpa.exists(jpaRepositoryImplementation, filter.build());
        } catch (InvalidDataAccessResourceUsageException e) {
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public long count(@NonNull Filter filter) {
        try {
            return OperationJpa.count(jpaRepositoryImplementation, filter.build());
        } catch (InvalidDataAccessResourceUsageException e) {
            log.error(e.getMessage());
        }
        return 0;
    }
}
