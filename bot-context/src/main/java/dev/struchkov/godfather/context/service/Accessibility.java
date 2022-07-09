package dev.struchkov.godfather.context.service;

import dev.struchkov.godfather.context.domain.content.Message;

public interface Accessibility {

    boolean check(Message message);

}
