package dev.struchkov.haiti.core.util;

import dev.struchkov.haiti.context.domain.BasicEntity;
import dev.struchkov.haiti.context.domain.ExistsContainer;
import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;
import dev.struchkov.haiti.context.repository.simple.CrudOperation;
import dev.struchkov.haiti.context.repository.simple.MultipleOperation;
import dev.struchkov.haiti.context.repository.simple.PagingOperation;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static dev.struchkov.haiti.utils.Exceptions.utilityClass;

public final class ServiceOperation {

    private ServiceOperation() {
        utilityClass();
    }

    public static <Entity extends BasicEntity<Key>, Key> void deleteAllById(MultipleOperation<Entity, Key> repository, Collection<Key> ids) {
        repository.deleteAllById(ids);
    }

    public static <Entity> Sheet<Entity> getAll(PagingOperation<Entity> repository, Pagination pagination) {
        return repository.findAll(pagination);
    }

    public static <Entity, Key> Optional<Entity> getById(CrudOperation<Entity, Key> repository, Key id) {
        return repository.findById(id);
    }

    public static <Entity, Key> boolean existsById(CrudOperation<Entity, Key> repository, Key id) {
        return repository.existsById(id);
    }

    public static <Entity, Key> void deleteById(CrudOperation<Entity, Key> repository, Key id) {
        repository.deleteById(id);
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
