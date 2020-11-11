package org.sadtech.haiti.context.repository;

import org.sadtech.haiti.context.repository.simple.CrudOperation;
import org.sadtech.haiti.context.repository.simple.MultipleOperation;
import org.sadtech.haiti.context.repository.simple.PagingOperation;

public interface SimpleManagerRepository<T, K> extends CrudOperation<T, K>, MultipleOperation<T, K>, PagingOperation<T> {
}
