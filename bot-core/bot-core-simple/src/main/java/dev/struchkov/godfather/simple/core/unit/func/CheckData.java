package dev.struchkov.godfather.simple.core.unit.func;

import dev.struchkov.godfather.main.domain.content.Message;

@FunctionalInterface
public interface CheckData<C extends Message> {

    boolean checked(C content);

}
