package dev.struchkov.godfather.core.service.provider;

import dev.struchkov.godfather.context.domain.content.Mail;
import dev.struchkov.godfather.context.service.EventProvider;
import dev.struchkov.godfather.core.GeneralAutoResponder;

public class EventStoryLineProvider implements EventProvider<Mail> {

    private final GeneralAutoResponder<Mail> generalAutoResponder;

    public EventStoryLineProvider(GeneralAutoResponder<Mail> generalAutoResponder) {
        this.generalAutoResponder = generalAutoResponder;
    }

    @Override
    public void sendEvent(Mail message) {
        generalAutoResponder.processingNewMessage(message);
    }

}
