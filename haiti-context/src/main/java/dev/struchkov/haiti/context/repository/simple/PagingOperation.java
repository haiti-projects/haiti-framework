package dev.struchkov.haiti.context.repository.simple;

import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;

public interface PagingOperation<T> {

    Sheet<T> findAll(Pagination pagination);

}
