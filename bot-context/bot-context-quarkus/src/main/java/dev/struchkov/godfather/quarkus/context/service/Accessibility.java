package dev.struchkov.godfather.quarkus.context.service;

import dev.struchkov.godfather.main.domain.content.Message;
import io.smallrye.mutiny.Uni;

public interface Accessibility {

    Uni<Void> check(Message message);

}
