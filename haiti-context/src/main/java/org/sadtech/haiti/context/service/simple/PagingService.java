package org.sadtech.haiti.context.service.simple;

import lombok.NonNull;
import org.sadtech.haiti.context.page.Pagination;
import org.sadtech.haiti.context.page.Sheet;

/**
 * Расширение базового контракта сервисов с добавлением пагинации.
 *
 * @param <T> Сущность сервиса.
 * @author upagge 26.05.20
 */
public interface PagingService<T> {

    Sheet<T> getAll(@NonNull Pagination pagination);

}
