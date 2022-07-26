package dev.struchkov.godfather.context.service.save;

import dev.struchkov.godfather.context.domain.content.Message;

@FunctionalInterface
public interface PreservableData<D, M extends Message> {

    D getData(M content);

}
