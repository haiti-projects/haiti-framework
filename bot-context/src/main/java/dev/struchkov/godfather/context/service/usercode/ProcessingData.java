package dev.struchkov.godfather.context.service.usercode;

import dev.struchkov.godfather.context.domain.BoxAnswer;

import java.util.Optional;

@FunctionalInterface
public interface ProcessingData<C> {

    Optional<BoxAnswer> processing(C content);

}
