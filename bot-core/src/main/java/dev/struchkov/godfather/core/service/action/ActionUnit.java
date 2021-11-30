package dev.struchkov.godfather.core.service.action;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.core.domain.unit.MainUnit;

/**
 * Интерфейс для обработки Unit-ов.
 *
 * @author upagge [11/07/2019]
 */
@FunctionalInterface
public interface ActionUnit<M extends MainUnit, C extends Message> {

    /**
     * Метод обработки Unit-а.
     *
     * @param unit    Unit, который необходимо обработать
     * @param content Запрос пользователя
     * @return Новый Unit, который может нуждаться в обработке
     */
    MainUnit action(M unit, C content);

}
