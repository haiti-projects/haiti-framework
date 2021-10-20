package dev.struchkov.haiti.core.util;

import dev.struchkov.haiti.context.domain.BasicEntity;
import dev.struchkov.haiti.context.domain.ExistsContainer;
import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.context.repository.simple.CrudOperation;
import dev.struchkov.haiti.context.repository.simple.MultipleOperation;
import dev.struchkov.haiti.context.repository.simple.PagingOperation;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public final class ServiceOperation {

    public static <T extends BasicEntity<K>, K> void deleteAllById(MultipleOperation<T, K> repository, Collection<K> primaryKeys) {
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

    public static <K, T extends BasicEntity<K>> ExistsContainer<T, K> existsContainerById(MultipleOperation<T, K> multipleOperation, Collection<K> ids) {
        final List<T> existsEntity = multipleOperation.findAllById(ids);
        final Set<K> existsIds = existsEntity.stream()
                .map(BasicEntity::getId)
                .collect(Collectors.toSet());
        if (existsIds.containsAll(ids)) {
            return ExistsContainer.allFind(existsEntity);
        } else {
            final Set<K> noExistsId = ids.stream()
                    .filter(id -> !existsIds.contains(id))
                    .collect(Collectors.toSet());
            return ExistsContainer.notAllFind(existsEntity, noExistsId);
        }
    }
}
