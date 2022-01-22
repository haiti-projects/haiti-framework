package dev.struchkov.haiti.filter;

import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;

import java.util.Optional;

public interface FilterOperation<T> {

    Sheet<T> findAll(Filter filter, Pagination pagination);

    Optional<T> findFirst(Filter filter);

    boolean exists(Filter filter);

    long count(Filter filter);

}
