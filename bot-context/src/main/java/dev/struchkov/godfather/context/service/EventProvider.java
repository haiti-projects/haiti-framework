package dev.struchkov.godfather.context.service;

import dev.struchkov.godfather.context.domain.content.Message;

public interface EventProvider<M extends Message> {

    void sendEvent(M message);

}
