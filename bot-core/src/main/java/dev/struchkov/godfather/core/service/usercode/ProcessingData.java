package dev.struchkov.godfather.core.service.usercode;

import dev.struchkov.godfather.context.domain.BoxAnswer;
import dev.struchkov.godfather.context.domain.content.Message;

@FunctionalInterface
public interface ProcessingData<C extends Message> {

    BoxAnswer processing(C content);

}
