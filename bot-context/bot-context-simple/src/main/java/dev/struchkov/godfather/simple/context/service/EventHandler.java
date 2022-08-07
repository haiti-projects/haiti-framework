package dev.struchkov.godfather.simple.context.service;

import dev.struchkov.godfather.main.domain.event.Event;

public interface EventHandler<T extends Event> {

    void handle(T event);

    String getEventType();

}
