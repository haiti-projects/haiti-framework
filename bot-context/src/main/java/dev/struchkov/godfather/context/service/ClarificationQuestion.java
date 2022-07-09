package dev.struchkov.godfather.context.service;

import dev.struchkov.godfather.context.domain.Clarification;
import dev.struchkov.godfather.context.domain.content.Message;

@FunctionalInterface
public interface ClarificationQuestion<C extends Message> {

    Clarification getClarification(C message);

}
