package dev.struchkov.godfather.simple.core.unit.func;

import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.simple.core.unit.MainUnit;

/**
 * TODO: Добавить описание интерфейса.
 *
 * @author upagge [04/08/2019]
 */
@FunctionalInterface
public interface CheckSave<M extends Message> {

    MainUnit<M> check(M content);

}
