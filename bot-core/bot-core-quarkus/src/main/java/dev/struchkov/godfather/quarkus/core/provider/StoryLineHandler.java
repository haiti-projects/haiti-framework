package dev.struchkov.godfather.quarkus.core.provider;

import dev.struchkov.godfather.main.domain.content.Mail;
import dev.struchkov.godfather.quarkus.context.service.EventHandler;
import dev.struchkov.godfather.quarkus.core.GeneralAutoResponder;
import io.smallrye.mutiny.Uni;

public class StoryLineHandler implements EventHandler<Mail> {

    private final GeneralAutoResponder<Mail> generalAutoResponder;

    public StoryLineHandler(GeneralAutoResponder<Mail> generalAutoResponder) {
        this.generalAutoResponder = generalAutoResponder;
    }

    @Override
    public Uni<Void> handle(Mail message) {
        return generalAutoResponder.processingNewMessage(message);
    }

    @Override
    public String getEventType() {
        return Mail.TYPE;
    }

}
