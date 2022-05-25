package dev.struchkov.godfather.context.service.usercode;

import dev.struchkov.godfather.context.domain.BoxAnswer;
import dev.struchkov.godfather.context.domain.content.Message;

public interface MessageFunction<M extends Message> {

    void build(M message, BoxAnswer.Builder builder);

}
