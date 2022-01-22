package dev.struchkov.haiti.context.service.simple;

import dev.struchkov.haiti.context.page.Pagination;
import dev.struchkov.haiti.context.page.Sheet;

/**
 * Расширение базового контракта сервисов с добавлением пагинации.
 *
 * @param <Entity> Сущность сервиса.
 * @author upagge 26.05.20
 */
public interface PagingService<Entity> {

    Sheet<Entity> getAll(Pagination pagination);

}
