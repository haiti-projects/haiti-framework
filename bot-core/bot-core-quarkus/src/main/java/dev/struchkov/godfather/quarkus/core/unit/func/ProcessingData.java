package dev.struchkov.godfather.quarkus.core.unit.func;

import dev.struchkov.godfather.main.domain.BoxAnswer;
import io.smallrye.mutiny.Uni;

@FunctionalInterface
public interface ProcessingData<C> {

    Uni<BoxAnswer> processing(C content);

}
