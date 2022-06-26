package dev.struchkov.godfather.core.service;

import dev.struchkov.godfather.context.domain.content.Message;

public interface Accessibility {

    boolean check(Message message);

}
