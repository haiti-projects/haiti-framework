package dev.struchkov.godfather.simple.context.service;

import java.util.Map;

/**
 * TODO: Добавить описание класса.
 *
 * @author upagge [13/07/2019]
 */
@FunctionalInterface
public interface Pusher<D> {

    void push(Long personId, Map<String, D> saveElement);

}
