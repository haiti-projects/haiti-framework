package dev.struchkov.godfather.context.service.save;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.MainUnit;

/**
 * TODO: Добавить описание интерфейса.
 *
 * @author upagge [04/08/2019]
 */
@FunctionalInterface
public interface CheckSave<M extends Message> {

    MainUnit<M> check(M content);

}
