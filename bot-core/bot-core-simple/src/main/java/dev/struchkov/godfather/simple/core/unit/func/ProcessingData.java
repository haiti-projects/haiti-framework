package dev.struchkov.godfather.simple.core.unit.func;

import dev.struchkov.godfather.main.domain.BoxAnswer;

import java.util.Optional;

@FunctionalInterface
public interface ProcessingData<C> {

    Optional<BoxAnswer> processing(C content);

}
