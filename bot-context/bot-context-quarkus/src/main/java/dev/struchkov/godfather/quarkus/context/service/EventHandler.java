package dev.struchkov.godfather.quarkus.context.service;

import dev.struchkov.godfather.main.domain.event.Event;
import io.smallrye.mutiny.Uni;

public interface EventHandler<T extends Event> {

    Uni<Void> handle(T event);

    String getEventType();

}
