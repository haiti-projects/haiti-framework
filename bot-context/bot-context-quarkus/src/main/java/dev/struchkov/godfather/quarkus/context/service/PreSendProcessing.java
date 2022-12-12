package dev.struchkov.godfather.quarkus.context.service;

import dev.struchkov.godfather.main.domain.BoxAnswer;
import io.smallrye.mutiny.Uni;

@FunctionalInterface
public interface PreSendProcessing {

    Uni<BoxAnswer> pretreatment(BoxAnswer boxAnswer);

}
