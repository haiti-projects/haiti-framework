package dev.struchkov.godfather.context.service.usercode;

import dev.struchkov.godfather.context.domain.BoxAnswer;

@FunctionalInterface
public interface ProcessingData<C> {

    BoxAnswer processing(C content);

}
