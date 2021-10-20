package dev.struchkov.haiti.filter;

import lombok.NonNull;
import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;

import java.util.Optional;

public interface FilterOperation<T> {

    Sheet<T> findAll(@NonNull Filter filter, @NonNull Pagination pagination);

    Optional<T> findFirst(@NonNull Filter filter);

    boolean exists(@NonNull Filter filter);

    long count(@NonNull Filter filter);

}
