package dev.struchkov.haiti.context.service.simple;

import lombok.NonNull;
import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;

/**
 * Расширение базового контракта сервисов с добавлением пагинации.
 *
 * @param <T> Сущность сервиса.
 * @author upagge 26.05.20
 */
public interface PagingService<T> {

    Sheet<T> getAll(@NonNull Pagination pagination);

}
