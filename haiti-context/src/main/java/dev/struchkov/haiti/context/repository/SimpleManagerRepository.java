package dev.struchkov.haiti.context.repository;

import dev.struchkov.haiti.context.repository.simple.CrudOperation;
import dev.struchkov.haiti.context.repository.simple.MultipleOperation;
import dev.struchkov.haiti.context.repository.simple.PagingOperation;
import dev.struchkov.haiti.context.domain.BasicEntity;

public interface SimpleManagerRepository<T extends BasicEntity<K>, K>
        extends CrudOperation<T, K>, MultipleOperation<T, K>, PagingOperation<T> {

}
