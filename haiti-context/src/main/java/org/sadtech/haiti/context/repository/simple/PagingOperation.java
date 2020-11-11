package org.sadtech.haiti.context.repository.simple;

import lombok.NonNull;
import org.sadtech.haiti.context.page.Pagination;
import org.sadtech.haiti.context.page.Sheet;

public interface PagingOperation<T> {

    Sheet<T> findAll(@NonNull Pagination pagination);

}
