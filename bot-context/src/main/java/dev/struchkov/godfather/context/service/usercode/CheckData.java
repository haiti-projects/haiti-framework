package dev.struchkov.godfather.context.service.usercode;

import dev.struchkov.godfather.context.domain.content.Message;

@FunctionalInterface
public interface CheckData<C extends Message> {

    boolean checked(C content);

}
