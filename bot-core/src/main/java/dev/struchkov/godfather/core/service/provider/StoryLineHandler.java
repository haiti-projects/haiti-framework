package dev.struchkov.godfather.core.service.provider;

import dev.struchkov.godfather.context.domain.content.Mail;
import dev.struchkov.godfather.context.service.EventHandler;
import dev.struchkov.godfather.core.GeneralAutoResponder;

public class StoryLineHandler implements EventHandler<Mail> {

    private final GeneralAutoResponder<Mail> generalAutoResponder;

    public StoryLineHandler(GeneralAutoResponder<Mail> generalAutoResponder) {
        this.generalAutoResponder = generalAutoResponder;
    }

    @Override
    public void handle(Mail message) {
        generalAutoResponder.processingNewMessage(message);
    }

    @Override
    public String getEventType() {
        return Mail.TYPE;
    }

}
