package dev.struchkov.godfather.quarkus.context.service;

import io.smallrye.mutiny.Uni;

import java.util.Map;

/**
 * TODO: Добавить описание класса.
 *
 * @author upagge [13/07/2019]
 */
@FunctionalInterface
public interface Pusher<D> {

    Uni<Void> push(String personId, Map<String, D> saveElement);

}
