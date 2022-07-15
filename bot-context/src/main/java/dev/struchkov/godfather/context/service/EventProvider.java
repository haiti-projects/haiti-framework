package dev.struchkov.godfather.context.service;

import dev.struchkov.godfather.context.domain.event.Event;

public interface EventProvider<T extends Event> {

    void sendEvent(T event);

    String getEventType();

}
