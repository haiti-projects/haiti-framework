package dev.struchkov.godfather.simple.core.unit.func;

import dev.struchkov.godfather.main.domain.content.Message;

@FunctionalInterface
public interface PreservableData<D, M extends Message> {

    D getData(M content);

}
