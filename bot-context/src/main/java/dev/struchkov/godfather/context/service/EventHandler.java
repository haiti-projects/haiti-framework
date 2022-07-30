package dev.struchkov.godfather.context.service;

import dev.struchkov.godfather.context.domain.event.Event;

public interface EventHandler<T extends Event> {

    void handle(T event);

    String getEventType();

}
