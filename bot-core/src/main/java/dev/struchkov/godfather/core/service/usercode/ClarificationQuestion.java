package dev.struchkov.godfather.core.service.usercode;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.core.domain.Clarification;

@FunctionalInterface
public interface ClarificationQuestion<C extends Message> {

    Clarification getClarification(C message);

}
