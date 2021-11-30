package dev.struchkov.godfather.core.service.save.data;

import dev.struchkov.godfather.context.domain.content.Message;

@FunctionalInterface
public interface PreservableData<E, C extends Message> {

    E getData(C content);

}
