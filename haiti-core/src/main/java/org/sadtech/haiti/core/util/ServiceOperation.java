package org.sadtech.haiti.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.sadtech.haiti.context.page.Pagination;
import org.sadtech.haiti.context.page.Sheet;
import org.sadtech.haiti.context.repository.simple.CrudOperation;
import org.sadtech.haiti.context.repository.simple.MultipleOperation;
import org.sadtech.haiti.context.repository.simple.PagingOperation;

import java.util.Collection;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ServiceOperation {

    public static <T, K> void deleteAllById(MultipleOperation<T, K> repository, Collection<K> primaryKeys) {
        repository.deleteAllById(primaryKeys);
    }

    public static <T> Sheet<T> getAll(PagingOperation<T> repository, Pagination pagination) {
        return repository.findAll(pagination);
    }

    public static <T, K> Optional<T> getById(CrudOperation<T, K> repository, K primaryKey) {
        return repository.findById(primaryKey);
    }

    public static <T, K> boolean existsById(CrudOperation<T, K> repository, K primaryKey) {
        return repository.existsById(primaryKey);
    }

    public static <T, K> void deleteById(CrudOperation<T, K> repository, K primaryKey) {
        repository.deleteById(primaryKey);
    }

}
